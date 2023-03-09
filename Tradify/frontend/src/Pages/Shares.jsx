import { Link } from "react-router-dom";
import ApiCall from "../Components/ApiCall";

const Shares = () => {
    const [shares, isLoading, error] = ApiCall("GET", "/shares", {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("access_token")}`,
        },
    });

    return (
        <div>
            {error && (
                <div className="alert alert-danger" role="alert">
                    <p>{error}, Please try again later.</p>
                </div>
            )}

            {isLoading ? (
                <p>Loading...</p>
            ) : (
                <>
                    <h1>Shares</h1>
                    <ul className="list-group">
                        {shares.map((share, i) => (
                            <Link
                                to={/trade/ + share.id}
                                className="nav-item nav-link text-muted"
                                key={i}
                            >
                                <span className="list-group-item">
                                    {share.name}
                                </span>
                            </Link>
                        ))}
                    </ul>
                </>
            )}
        </div>
    );
};

export default Shares;
