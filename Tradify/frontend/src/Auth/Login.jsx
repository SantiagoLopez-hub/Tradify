import { Navigate } from "react-router-dom";
import { useState } from "react";
import axios from "axios";

const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(false);
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault();
        setError(false);

        axios
            .post(
                process.env.REACT_APP_DOMAIN + "/login",
                new URLSearchParams({
                    username: username,
                    password: password,
                })
            )
            .then((response) => {
                localStorage.setItem(
                    "access_token",
                    response?.data?.access_token
                );
                localStorage.setItem(
                    "refresh_token",
                    response?.data?.refresh_token
                );
                localStorage.setItem("username", username);
                setIsLoggedIn(true);
            })
            .catch((err) => {
                setError(err.message);
            });
    };

    return !isLoggedIn ? (
        <header className="App-header">
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="username" className="form-label">
                        Username
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="username"
                        aria-describedby="username"
                        onChange={(e) => {
                            setUsername(e.target.value);
                        }}
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="password" className="form-label">
                        Password
                    </label>
                    <input
                        type="password"
                        className="form-control"
                        id="password"
                        onChange={(e) => {
                            setPassword(e.target.value);
                        }}
                    />
                </div>
                <button type="submit" className="btn btn-primary">
                    Login
                </button>

                {error && (
                    <div className="alert alert-danger" role="alert">
                        <p>{error}</p>
                    </div>
                )}
            </form>
        </header>
    ) : (
        <Navigate to="/" />
    );
};

export default Login;
