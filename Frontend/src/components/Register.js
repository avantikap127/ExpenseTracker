import React, { useState } from "react";
import axios from "axios";

function Register({ setShowLogin }) {

  const [name,setName] = useState("");
  const [email,setEmail] = useState("");
  const [password,setPassword] = useState("");

  const registerUser = () => {

    axios.post(
      "https://expensetracker-production-7732.up.railway.app/api/auth/register",
      {
        name:name,
        email:email,
        password:password
      }
    )
    .then(() => {

      alert("Account Created Successfully");

      setShowLogin(true);

    })
    .catch(() => alert("Registration failed"));

  };

  return (
    <div style={{textAlign:"center",marginTop:"120px"}}>

      <h2>Create Account</h2>

      <input placeholder="Name"
      onChange={(e)=>setName(e.target.value)} />

      <br/><br/>

      <input placeholder="Email"
      onChange={(e)=>setEmail(e.target.value)} />

      <br/><br/>

      <input type="password" placeholder="Password"
      onChange={(e)=>setPassword(e.target.value)} />

      <br/><br/>

      <button onClick={registerUser}>Register</button>

      <p>
        Already have account?
        <button onClick={()=>setShowLogin(true)}>Login</button>
      </p>

    </div>
  );
}

export default Register;
