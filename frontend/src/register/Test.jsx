import {useState} from "react"
import {getTheFile} from "../api/ViewerAPI"


export default function Test(){
    
    const test = async()=>{

        const response = await getTheFile();
        return response

    }    

    const handleSubmit = (e)=>{
        e.preventDefault()
        test()
    }

    return(
        <form onSubmit = {handleSubmit}>
            <button>Click here </button>
        </form>
    )


}