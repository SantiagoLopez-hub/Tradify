import ApiCall from "../Components/ApiCall";

const Users = () => {
    const [users, isLoading, error] = ApiCall("GET", "/users", {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("access_token")}`,
        },
    });

    return (
        <header className="App-header">
            {error && (
                <div className="alert alert-danger" role="alert">
                    <p>{error}, Please try again later.</p>
                </div>
            )}

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
