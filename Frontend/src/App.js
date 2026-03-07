import React, { useState } from "react";
import Dashboard from "./components/Dashboard";
import Login from "./components/Login";
import "./App.css";

function App() {

  const [loggedIn, setLoggedIn] = useState(
    localStorage.getItem("token") !== null
  );

  if (!loggedIn) {
    return <Login setLoggedIn={setLoggedIn} />;
  }

  return <Dashboard />;
}

export default App;
