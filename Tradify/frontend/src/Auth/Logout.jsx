import { Link } from "react-router-dom";

const Logout = () => {
    localStorage.clear();

    return (
        <>
            <div>Logged out successfully</div>
            <Link className="nav-link mt-5" to="/">
                <button className="btn btn-primary">Home</button>
            </Link>
        </>
    );
};

export default Logout;
