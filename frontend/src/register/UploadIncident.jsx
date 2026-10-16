import {useState} from "react"
import {uploadFile} from "../api/ViewerAPI"

export default function Upload(){

    const [files,setFiles] = useState([])

    let exist = false;

    if(files.length > 0){
        exist = true
    }
    
    function addFiles(e){
        const fileList = Array.from(e.target.files);
        setFiles(prev=>([...prev,...fileList]))
        
    }

    const upload = async()=>{
        const formData = new FormData();

        files.forEach((file)=>(
            formData.append("files",file)  
        ))

        const response = await uploadFile(formData)  
    }


    //Formdata is more like a dictionary that is key value pair so we need to make sure that we send each file
    //not a list of file.

    // of- entire FileList
    // from - just files inside the fileList

    function handleSubmit(e){
        e.preventDefault()
        upload()
    }

    function deleteFiles(itemIndex){
    const newFileList = files.filter((file,index)=>(index !== itemIndex))     // 0! =1 
    setFiles(newFileList)
    }

    return(
        <form onSubmit = {handleSubmit}>
        <label>Upload your file: </label>
        <input type="file" onChange ={addFiles}></input><br></br>
        <button type="submit">Submit</button>

        {!exist ? <p>Please upload a file</p> : 
        files.map((item,index)=>(
            <ul key = {index}>
                <li>{item.name}</li>
                <button type="button" onClick = {()=>deleteFiles(index)}>Delete</button>
            </ul>
        ))      
        }
        </form>
    )
}
