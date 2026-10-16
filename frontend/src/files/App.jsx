import { useState } from 'react'
import Login from "../register/login.jsx"
import Register from "../register/Register.jsx"
import UploadIncident from "../register/UploadIncident.jsx"
import {AuthProvider} from "../auth/AuthContext.jsx"
import Test from "../register/Test.jsx"
import User from "./User.jsx"
function App() {

return (
  <>
 
  <AuthProvider>
     <Register></Register>
      <Login></Login>
      <UploadIncident></UploadIncident>
      <Test/>
  </AuthProvider>
  
  </>
  
)
}

export default App
