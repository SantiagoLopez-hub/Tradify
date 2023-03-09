const ApiError = ({ error }) => {
    return (
        <div className="alert alert-danger" role="alert">
            <p>{error}, Please try again later.</p>
        </div>
    );
};

export default ApiError;
