import privateApi from "../api/privateAxios"
import publicApi from "../api/publicAxios"
import {useAuth} from "../auth/AuthContext"

export const createUser = async (formData) =>{
    const response = await publicApi.post("user/register",formData)
    return response.data
}

export const loginUser = async (loginData) =>{
    const response = await publicApi.post("user/login",loginData)
    return response.data    
}

export const getUsers = async () =>{
    const response = await privateApi.get("user/all")
    
    return response.data
}

export const responseEndpoint = async() =>{
    const response = await publicApi.post("user/refresh");
    return response;
}

export const uploadFile = async(formData)=>{
    const response = await privateApi.post("document/add",formData);
    return response;

}

export const getTheFile = async()=>{
    const response = await privateApi.post("/document/1")
    return response;
}

