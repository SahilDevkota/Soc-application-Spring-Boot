from agents.chat_model import groq_model
from agents.defender import call_defender
from langchain.tools import tool 
from typing import TypedDict,Annotated
from langchain.messages import AnyMessage
import operator
from pydantic import BaseModel
from typing import Dict
from langchain.messages import SystemMessage
from langchain.messages import ToolMessage
from typing import Literal
from langgraph.graph import StateGraph,START,END
from IPython.display import display,Image
import json
from langchain.messages import HumanMessage

def call_analyzer(file):

    class modelStructuredOutput(BaseModel):
        event : str
        risk_level : str

    structured_model = groq_model.with_structured_output(modelStructuredOutput,method="json_schema")


   
    @tool 
    def call_defender_agent(log: str, risk_level:str)-> str:
        """Calls the defender agent"""
        return call_defender(log,risk_level)

    tools = [call_defender_agent]
    model_with_tools = groq_model.bind_tools(tools)

    class MessageState(TypedDict):
        messages: Annotated[list[AnyMessage],operator.add]
        risk_level : str
        event : str

    def llm_call(state: Dict):
        """Call the LLM model"""

        structured_prompt = f"""You are a Cyber Analyst, who has years of experience finding threats, writing reports, identifying suspicious login attempts and maintaining logs. 
        A company just faced a phishing attack, and wants you to act and call the defender when it's necessary.
            
        Whenever there is any attempt on the account, after certain criteria, you will be provided with the list of log file that contains information such as username, number of failed attempts and so on. 
        If the number of failed attempt is more than 4, then you will first step back and analyze the log file carefully.
        
        List of log file:
        {file}               

        The report must be professionally written, and it should also contain information about the event that caused it.
        The risk level must be either of one i.e 'HIGH','LOW','MEDIUM'."""

        

        systemMessage1 = SystemMessage(content=structured_prompt)
       



        response2 =  structured_model.invoke(
            [
                systemMessage1
            ] + 
            state['messages']
        )

        print(response2)

        json_response = response2.model_dump_json()

        tool_model_prompt = f"""
            You are a Cyber Analyst, who has years of experience finding threats, writing reports, identifying suspicious login attempts and maintaining logs. A company just faced a phishing attack, and wants you to act and call the defender when it's necessary.
            You are given a set of tools that can be used when required. 

            Tools :
            {call_defender_agent}
            Whenever there is any attempt on the account, after certain criteria, you will be provided with the list of log file that contains information such as username, number of failed attempts and so on. 
            If the number of failed attempt is exactly or more than 4, then you will first step back and analyze the response carefully. 
            
            response: 
            {json_response}
            
            Once, you've analyzed the response, you got two option.
            The first one is to keep monitoring, if the login attempt is exactly or less than 4.
            If the failed attempt is more than 4, immedieately call the defender agent.                       


            Before calling the defender agent, you need to carefully examine the response and write a report and risk level 
            in dictionary format. Then, use the tool to call the defender agent and passed the output as argument.

            
            The report must be professionally written, and it should also contain information about the event that caused it.
            The risk level must be either of one i.e 'HIGH','LOW','MEDIUM'.
            
            The dictionary should have event and risk_level keys. And should look something like:
            {{"event" : "Around 10 a.m, in this morning, Raj had 10 attempts for his account.Even if you forget your password, you can easily change it, by clicking 'forgot_password' option. Instead, he kept on logging in.That means, somethings wrong. I will call the defender agent, to disable his account for a while until further notice from the admin. ","risk_level" :"HIGH"}}
            The event must be explained in detail and should answer the 5W and 1H question i.e. why,where,who,what,whose,how
   
            Make sure, if you're confused or you don't know what to do, just simply say "I don't know".
            And, keep your focus only on the log file, instead of creating your own answer."""

        systemMessage2 = SystemMessage(content=tool_model_prompt)

        response1 = model_with_tools.invoke(
            [
                systemMessage2
            ] + 
            state['messages']
        )
        
        return {
            "messages" : [response1],
            "risk_level" : response2.risk_level,
            "event" : response2.event      
        }

    def tool_node(state:dict):
        """Calls the tool"""

        messages = state['messages']
        last_message = messages[-1]
        result = []

        for tool_call in last_message.tool_calls:
            id = tool_call['id']
            args = tool_call['args']
            observation = call_defender_agent.invoke(args)
            result.append(ToolMessage(tool_call_id = id,content = observation))
        return {"messages": result}

    def should_continue(state:MessageState)->Literal['tool_node','END']:
        """Decide whether to continue or stop"""

        messages = state['messages']
        last_message = messages[-1]

        if last_message.tool_calls:
            return "tool_node"

        return END 

    agent_builder  = StateGraph(MessageState)

    agent_builder.add_node('llm_call',llm_call)
    agent_builder.add_node('tool_node',tool_node)
    agent_builder.add_edge(START,'llm_call')
    agent_builder.add_conditional_edges(
        "llm_call",
        should_continue,
        ["tool_node",END]
    )
    agent_builder.add_edge('tool_node','llm_call')

    agent = agent_builder.compile()


    display(Image(agent.get_graph(xray=True).draw_mermaid_png()))

    human_message = HumanMessage(content="Investigate this suspiciouse Login")
    response = agent.invoke({
        "messages" : [human_message]
    })     

  
    return response