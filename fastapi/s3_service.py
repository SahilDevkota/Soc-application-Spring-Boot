from config import access_key,secret_key,region,bucket
import boto3

s3 = boto3.client("s3"
                  ,aws_access_key_id = access_key
                  ,aws_secret_access_key = secret_key
                    ,region_name = region
)

