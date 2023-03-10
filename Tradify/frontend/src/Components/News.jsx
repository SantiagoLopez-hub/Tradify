import ApiCall from "./ApiCall";

const News = ({ share_id }) => {
    const [news, isLoading, error] = ApiCall("GET", "/news/share/" + share_id, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("access_token")}`,
        },
    });

    return (
        <div>
            <h1>News</h1>
            {error && <p>{error}</p>}
            {isLoading ? (
                <p>Loading...</p>
            ) : (
                <ul>
                    {news.map((news) => (
                        <li key={news.id}>
                            <p>{news.title}</p>
                            <p>{news.content}</p>
                            <p>{news.date}</p>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default News;
