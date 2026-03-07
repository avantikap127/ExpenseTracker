import React, { useState } from "react";
import Dashboard from "./components/Dashboard";
import Login from "./components/Login";
import Register from "./components/Register";
import "./App.css";

function App(){

const [loggedIn,setLoggedIn] = useState(
localStorage.getItem("token") !== null
);

const [showLogin,setShowLogin] = useState(true);

if(!loggedIn){

return showLogin ?
<Login setLoggedIn={setLoggedIn} setShowLogin={setShowLogin}/>
:
<Register setShowLogin={setShowLogin}/>

}

return <Dashboard/>

}

export default App;


