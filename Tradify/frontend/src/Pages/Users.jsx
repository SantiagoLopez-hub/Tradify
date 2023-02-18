import ApiCall from "../Components/ApiCall";

const Users = () => {
    const [users, isLoading, error] = ApiCall("GET", "/users", {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("access_token")}`,
        },
    });

    if (error) {
        return (
            <div className="App">
                <header className="App-header">
                    {error}, Please try again later.
                </header>
            </div>
        );
    }

    return (
        <header className="App-header">
            Learn React
            <ul>
                {isLoading ? (
                    <p>Loading...</p>
                ) : (
                    users.map((user, i) => <li key={i}>{user.email}</li>)
                )}
            </ul>
        </header>
    );
};

export default Users;
