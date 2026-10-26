from agents.chat_model import groq_model 
from langchain.tools import tool
import os
from dotenv import load_dotenv
import requests
from typing import TypedDict,Annotated
import operator
from langchain.messages import AnyMessage
from langchain.messages import SystemMessage
from typing import Dict
from langchain.messages import ToolMessage
from langgraph.graph import StateGraph,START,END
from typing import Literal
from langchain.messages import HumanMessage



def call_defender(log: str, risk_level:str):


    load_dotenv()
    BASE_URL = os.getenv("BASE_URL")
    


    
    @tool
    def disable_account(username:str, disabled: bool):
        """Calls the API to disable the account"""

        response = requests.post(BASE_URL +"disable",
                                {
                                    "username":username,
                                    "isDisabled" : disabled
                                })

        print(response)
        return response.text
    

    model_with_tools = groq_model.bind_tools([disable_account])

    class MessageState(TypedDict):
        messages: Annotated[list[AnyMessage],operator.add]
        llm_call: int

    def llm_call(state:Dict):

        """Call the LLM model"""

        systemMessage = f"You are an experienced blue teamer." \
        "You will be given a log file alongside with risk level. Carefully examine the file and before you take any decision step back and think for a while." \
        "Log File:" \
        f"{log}" \
        "risk level:" \
        f"{risk_level}" \
        "A set of tools will be provided for you. Only use them when needed" \
        "Tools:" \
        "{disable_account}" \
        "Conditions:" \
        "1. If the call is made, step back and carefully examine the log file." \
        "2. Extract the username from the log file, as it will be later required, when disabling the account"\
        "3. Check criteria such as risk level and number of attempts." \
        "4. To disable an account, the risk level should be either Medium or High" \
        "5. To disable an account, the number of attempts must be higher than 6"
        
        "6. However, don't only check those criteria" \
        "7. Check the description, and if needed, use the tool to disable the account." \
        "If you are unsure what to do, simply say 'I don't know'. Make sure you don't hallucinate. " \
        "If the account is disabled, return a message saying 'Account disabled' and generate a report for the reason behind disabling the account (only if the account is disabled)"

        response = model_with_tools.invoke(
        [
            SystemMessage(content = systemMessage)

        ] + state['messages']
    )

        return {
            "messages": [response],
            "llm_call" : state.get('llm_calls',0) + 1
        }

    def tool_node(state:Dict):
        """Call the tool"""

        messages = state['messages']
        last_message = messages[-1]
        result = []

        for tool_call in last_message.tool_calls:
            id = tool_call['id']
            args = tool_call['args']
            observation = disable_account.invoke(args)
            result.append(ToolMessage(tool_call_id = id,content = observation))
        return {"messages": result}

    def should_continue(state:MessageState)->Literal["tool_node",END]:
        message = state['messages']
        last_message = message[-1]

        if last_message.tool_calls:
            return "tool_node"

        return END

    agent_builder = StateGraph(MessageState)

    agent_builder.add_node("llm_call",llm_call)
    agent_builder.add_node("tool_node",tool_node)
    agent_builder.add_edge(START,"llm_call")

    agent_builder.add_conditional_edges(
        "llm_call",
        should_continue,
        ["tool_node",END]
    )

    agent_builder.add_edge("tool_node","llm_call")

    agent = agent_builder.compile()

    human_message = HumanMessage(content="Investigate the report, and decide whether to the account or not.")

    response = agent.invoke(
        {"messages": [human_message]}
    )


    return response