import { Link } from "react-router-dom";

const Footer = () => {
    return (
        <footer className="mt-auto bg-dark">
            <ul className="navbar justify-content-center">
                <Link to="/" className="nav-item nav-link px-2 text-muted">
                    Home
                </Link>
                <Link to="/users" className="nav-item nav-link px-2 text-muted">
                    Users
                </Link>
            </ul>

            <p className="text-center text-muted">
                © 2023 Tradify - Goldsmiths, University of London
            </p>
        </footer>
    );
};

export default Footer;
