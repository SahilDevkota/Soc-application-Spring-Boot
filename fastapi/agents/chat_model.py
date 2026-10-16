from langchain_groq import ChatGroq
from dotenv import load_dotenv
import os
from pathlib import Path


env_path  = Path(__file__).resolve().parent.parent / "credentials.env"
load_dotenv(env_path)

groq_api_key = os.getenv("GROQ_API_KEY")

groq_model = ChatGroq(
    model = "openai/gpt-oss-20b",
    temperature = 0,
    api_key = groq_api_key,
)