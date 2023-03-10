import { useState } from "react";
import ApiCall from "../Components/ApiCall";

const Register = () => {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [address, setAddress] = useState("");
    const [city, setCity] = useState("");
    const [postCode, setPostCode] = useState("");
    const [country, setCountry] = useState("");
    const [phone, setPhone] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault();
        setError(false);

        const [user, isLoading, err] = ApiCall("POST", "/users/create", {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("access_token")}`,
            },
        });

        console.log(user);
    };

    return (
        <header className="App-header">
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="firstName" className="form-label">
                        First name
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="firstName"
                        aria-describedby="firstName"
                        onChange={(e) => {
                            setFirstName(e.target.value);
                        }}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="lastName" className="form-label">
                        Last name
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="lastName"
                        aria-describedby="lastName"
                        onChange={(e) => {
                            setLastName(e.target.value);
                        }}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">
                        Email
                    </label>
                    <input
                        type="email"
                        className="form-control"
                        id="email"
                        aria-describedby="email"
                        onChange={(e) => {
                            setEmail(e.target.value);
                        }}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="address" className="form-label">
                        Address
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="address"
                        aria-describedby="address"
                        onChange={(e) => {
                            setAddress(e.target.value);
                        }}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="city" className="form-label">
                        City
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="city"
                        aria-describedby="city"
                        onChange={(e) => {
                            setCity(e.target.value);
                        }}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="postCode" className="form-label">
                        Postcode
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="postCode"
                        aria-describedby="postCode"
                        onChange={(e) => {
                            setPostCode(e.target.value);
                        }}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="country" className="form-label">
                        Country
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="country"
                        aria-describedby="country"
                        onChange={(e) => {
                            setCountry(e.target.value);
                        }}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="phone" className="form-label">
                        Phone number
                    </label>
                    <input
                        type="phone"
                        className="form-control"
                        id="phone"
                        aria-describedby="phone"
                        onChange={(e) => {
                            setPhone(e.target.value);
                        }}
                        required
                    />
                </div>
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
                        required
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
                        required
                    />
                </div>
                <button type="submit" className="btn btn-primary">
                    Register
                </button>

                {error && (
                    <div className="alert alert-danger" role="alert">
                        <p>{error}</p>
                    </div>
                )}
            </form>
        </header>
    );
};

export default Register;
