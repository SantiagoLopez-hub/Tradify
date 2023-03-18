import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Auth/Login";
import Logout from "./Auth/Logout";
import Register from "./Auth/Register";
import Home from "./Pages/Home";
import Users from "./Pages/Users";
import Stocks from "./Pages/Stocks";
import Trade from "./Pages/Trade";
import Portfolio from "./Pages/Portfolio";
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
                    <Route exact path="/stocks" element={<Stocks />} />
                    <Route exact path="/portfolio" element={<Portfolio />} />
                    <Route exact path="/trade/:id" element={<Trade />} />
                    <Route exact path="/register" element={<Register />} />
                    <Route exact path="/login" element={<Login />} />
                    <Route exact path="/logout" element={<Logout />} />
                </Routes>

                <Footer />
            </div>
        </Router>
    );
};

export default App;
