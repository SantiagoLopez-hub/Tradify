import { Link } from "react-router-dom";

const Header = () => {
    return (
        <header
            className="sticky-top navbar
            border-bottom navbar-expand-lg
            bg-white navbar-light"
        >
            <h1 className="display-5">Tradify</h1>

            <button
                className="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation"
            >
                <span className="navbar-toggler-icon"></span>
            </button>

            <ul
                id="navbarSupportedContent"
                className="navbar-nav collapse me-auto navbar-collapse justify-content-end"
            >
                <li className="nav-item px-4">
                    <Link className="nav-link" to="/">
                        Home
                    </Link>
                </li>
                <li className="nav-item px-4">
                    <Link className="nav-link" to="/register">
                        Register
                    </Link>
                </li>
                <li className="nav-item px-4">
                    <Link className="nav-link" to="/login">
                        Login
                    </Link>
                </li>
                <li className="nav-item px-4">
                    <Link className="nav-link" to="/portfolio">
                        Portfolio
                    </Link>
                </li>
                <li className="nav-item px-4">
                    <Link className="nav-link" to="/stocks">
                        Shares
                    </Link>
                </li>
                <li className="nav-item px-4">
                    <Link className="nav-link" to="/users">
                        Users
                    </Link>
                </li>
                <li className="nav-item px-4">
                    <Link className="nav-link" to="/logout">
                        Logout
                    </Link>
                </li>
            </ul>
        </header>
    );
};

export default Header;
