import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./Pages/Home";
import Login from "./Auth/Login";
import Logout from "./Auth/Logout";
import Users from "./Pages/Users";
import Header from "./Components/Header";
import Footer from "./Components/Footer";
import "./App.css";

const App = () => {
    return (
        <Router>
            <div className="App">
                <Header />

                <Routes>
                    <Route exact path="/" element={<Home />} />
                    <Route exact path="/users" element={<Users />} />
                    <Route exact path="/login" element={<Login />} />
                    <Route exact path="/logout" element={<Logout />} />
                </Routes>

                <Footer />
            </div>
        </Router>
    );
};

export default App;
