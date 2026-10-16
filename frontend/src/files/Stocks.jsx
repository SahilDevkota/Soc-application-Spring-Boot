import {useState} from "react"
import {getStocks} from "../api/ViewerAPI"

export default function SearchStocks(){
     
    const [inputText,setInputText] = useState("")
    const [stockList,setStockList] = useState([])

    const stocks = async ()=>{
        const data = await getStocks("As")
        console.log(data)
    }

    const handleSubmit = (e)=>{
        e.preventDefault()
        stocks()
    }

    return(
        <form onSubmit = {handleSubmit}>
            <label>Enter the name: </label>
            <input value={inputText} onChange = {(e)=>setInputText(e.target.value)}></input><br></br>
            <button>Eta</button>
        </form>
    )
}