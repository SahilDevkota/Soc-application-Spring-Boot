from tika import parser
from fastapi import Response
from langchain_core.documents import Document
import shared

async def read_file(file):
    data = await file.read()
    read_parsed_content(data)
    return Response(
        content = data,
        media_type =  file.content_type
    )

def read_parsed_content(data):
    parsed = parser.from_buffer(data)
    
    shared.document =  Document(
        metadata = parsed['metadata'],
        page_content = parsed['content']
    )
    



