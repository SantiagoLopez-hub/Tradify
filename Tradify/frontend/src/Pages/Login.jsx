import ApiCall from "../Components/ApiCall";

const Login = () => {
    const [login, isLoading, error] = ApiCall(
        "POST",
        "/login",
        new URLSearchParams({
            username: "username",
            password: "1234",
        })
    );

    if (error) {
        return (
            <div className="App">
                <header className="App-header">
                    {error}, Please try again later.
                </header>
            </div>
        );
    }

    localStorage.setItem("access_token", login?.access_token);

    return (
        <header className="App-header">
            <form>
                <div className="mb-3">
                    <label htmlFor="username" className="form-label">
                        Username
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="username"
                        aria-describedby="username"
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
                    />
                </div>
                <button type="submit" className="btn btn-primary">
                    Submit
                </button>
            </form>
        </header>
    );
};

export default Login;
