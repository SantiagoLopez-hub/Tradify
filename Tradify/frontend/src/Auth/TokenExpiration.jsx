const isTokenExpired = (token) => {
    return (
        Date.now() >= JSON.parse(window.atob(token.split(".")[1])).exp * 1000
    );
};

export default isTokenExpired;
