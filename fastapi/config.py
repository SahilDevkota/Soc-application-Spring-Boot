import os 
from dotenv import load_dotenv


load_dotenv()
access_key = os.getenv("AWS_ACCESS_KEY")
secret_key = os.getenv("AWS_SECRET_KEY")
region = os.getenv("AWS_REGION")
bucket = os.getenv("AWS_BUCKET_NAME")
