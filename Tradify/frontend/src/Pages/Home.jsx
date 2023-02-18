const Home = () => {
    console.log("accessToken", localStorage.getItem("access_token"));

    return (
        <div>
            <p>Home</p>
        </div>
    );
};

export default Home;
