import {getUsers} from "../api/ViewerAPI"
import privateApi from "../api/privateAxios"
import {useAuth} from "../auth/AuthContext"
export default function User(){
    
    const userList = async ()=>{

    try{
        const data = await getUsers()
        console.log(data)
    }
    catch(error){
        console.log(error)
    }
}
    const handleSubmit = (e)=>{
        e.preventDefault();
        userList()
    }


    return(
        <form onSubmit = {handleSubmit}>
            <button>Click here</button>
        </form>
    )

}

