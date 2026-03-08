import React, { useState } from "react";
import axios from "axios";

function Login({ setLoggedIn, setShowLogin }) {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const loginUser = () => {

    axios.post(
      "https://expensetracker-production-7732.up.railway.app/api/auth/login",
      {
        email: email,
        password: password
      }
    )
    .then(res => {

      localStorage.setItem("token", res.data);

      alert("Login Successful");

      setLoggedIn(true);

    })
    .catch(err => {
      alert("Login Failed");
    });

  };

  return (
    <div style={{ textAlign: "center", marginTop: "100px" }}>

      <h2>Login</h2>

      <input
        placeholder="Email"
        onChange={(e) => setEmail(e.target.value)}
      />

      <br /><br />

      <input
        type="password"
        placeholder="Password"
        onChange={(e) => setPassword(e.target.value)}
      />

      <br /><br />

      <button onClick={loginUser}>
        Login
      </button>

      <br /><br />

      {/* Create Account Button */}

      <p>
        New user?
        <button
          onClick={() => setShowLogin(false)}
          style={{
            marginLeft: "10px",
            padding: "5px 10px",
            cursor: "pointer"
          }}
        >
          Create Account
        </button>
      </p>

    </div>
  );
}

export default Login;
