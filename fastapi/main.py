from fastapi import FastAPI, HTTPException, UploadFile, File
from pydantic import BaseModel
from datetime import datetime
from s3_service import s3
from parsed_content import read_file 
from agents.analyzer import call_analyzer   

app = FastAPI()



class Logback(BaseModel):
    username :str 
    event : str
    failedAttempts : int
    timestamp: datetime 

class Person(BaseModel):
    username:str
    age:int

@app.get("/test")
def test():
    return s3.list_buckets()

@app.post("/getTheFile")
async def getFile(file:UploadFile = File(...)):
    print(file.filename)
    content = await read_file(file)
    return content

@app.post("/callAnalyzer")
def analyzer(logback : list[Logback]):
    return call_analyzer(logback)
   
    