import {useState} from "react"
import privateApi from "../api/privateAxios"
import {useAuth} from "../auth/AuthContext"
import {loginUser} from "../api/ViewerAPI"


export default function Login(){
    const [loginData,setLoginData] = useState({
        "username": "",
        "password": ""
    })

    const {login} = useAuth();
    


    function change(e){
        setLoginData((prev)=>({...prev,[e.target.name]:e.target.value}))
    }
   
    const [showPassword,setShowPassword] = useState(false)

    const buttonText =showPassword ? "hide" : "show"
    
    const passwordType = showPassword ? "text" : "password"

    function showHidePassword(){
        setShowPassword((prev)=>!prev)
    }

    
    const loginPage = async()=>{
        try{
            
            const data = await loginUser(loginData)
            login(data)
            console.log("user login successfully")
            console.log(data)
        }
        catch(error){
            console.log(error)
        }
    }
        
    const handleSubmit = e =>{
        e.preventDefault()
        loginPage()
    }
 


    return(
        <form onSubmit = {handleSubmit}>
        <label htmlFor="username">Username: </label>
        <input type="text" name = "username" value ={loginData.username} onChange ={change}></input><br></br>
        <label htmlFor = "password">Password: </label>
        <input type= {passwordType} autoComplete ="current-password" name="password" value={loginData.password} onChange = {change}></input>
        <button type = "button" onClick = {showHidePassword} >{buttonText}</button><br></br>
        <button type ="submit">Submit</button>

        </form>
    )


}