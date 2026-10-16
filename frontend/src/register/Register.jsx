import {useState} from "react"
import {createUser} from "../api/ViewerAPI"
import axios from "axios"
export default function register(){
    
    const [formData,setFormData] = useState({
        "firstName": "",
        "lastName" : "",
        "password": "",
        "username":"",
        "email":"",
        "dateOfBirth":""
    });


    const register = async()=>{
        try{
            const data = await createUser(formData)
            console.log(data)
        }
        catch(error){
            console.log(error)
        }
    }

    const handleSubmit = (e)=>{
        e.preventDefault();
        register()
    }

    
    function change(e){
        setFormData((prev)=>({...prev,[e.target.name]:e.target.value}));
    }


    return(
        <form onSubmit = {handleSubmit}>
        <label>First Name: </label>
        <input type="text" name = "firstName" value = {formData.firstName} onChange = {change}></input><br></br>
        <label>Last Name: </label>
        <input type="text" name = "lastName" value = {formData.lastName} onChange = {change} ></input><br></br>
        <label>Username: </label>
        <input type="text" name = "username" placeholder = "user123" value={formData.username} onChange ={change}></input><br></br>
        <label>Password:  </label>   
        <input type="current-password" name = "password" minLength = "8" maxLength = "20" value = {formData.password} onChange ={change}></input><br></br>
        <label>Email: </label>
        <input type="email" name = "email" value = {formData.email} onChange ={change}></input><br></br>
        <label>Date of Birth: </label>
        <input type="date" name = "dateOfBirth" value = {formData.dateOfBirth} onChange ={change}></input><br></br>
        <button>Submit</button>
        </form>
    )
} 