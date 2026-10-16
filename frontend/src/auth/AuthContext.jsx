import {useState,useContext, createContext} from "react"

const AuthContext = createContext();

export function AuthProvider({children}){

    const [AccessToken,setAccessToken] = useState("");
    // const [RefreshToken,setRefreshToken] = useState("");


    const login = (data)=>{

       
        setAccessToken(data["AccessToken"])
        localStorage.setItem("accessToken",data.AccessToken)

 
    }

    const logout = ()=>{
        setAccessToken(null)


        localStorage.removeItem("accessToken")
    }

    
    
    return(
        <AuthContext.Provider
        value ={
            {
                AccessToken,
               // RefreshToken,
                login,
                logout
            }
        }
        >{children}</AuthContext.Provider>
    )

}

export function useAuth(){
    return useContext(AuthContext)
}