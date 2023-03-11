import ApiCall from "../Components/ApiCall";
import ApiError from "../Components/ApiError";

const Users = () => {
    const [users, isLoading, error] = ApiCall("GET", "/users", {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("access_token")}`,
        },
    });

    return (
        <div>
            {error && <ApiError error={error} />}

            <ul>
                {isLoading ? (
                    <p>Loading...</p>
                ) : (
                    <>
                        <h1>Users</h1>
                        {users.map((user, i) => (
                            <li key={i}>{user.email}</li>
                        ))}
                    </>
                )}
            </ul>
        </div>
    );
};

export default Users;
