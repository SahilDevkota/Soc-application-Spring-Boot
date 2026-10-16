import axios from "axios"
const BASE_URL = "http://localhost:8080/"

const publicApi =  axios.create({
    baseURL : BASE_URL,
    withCredentials: true
})


export default publicApi;