import ApiCall from "./ApiCall";

const News = ({ share_id }) => {
    const [news] = ApiCall("GET", "/news/share/" + share_id, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("access_token")}`,
        },
    });

    return news.length === 0 ? (
        "No news published yet"
    ) : (
        <table className="table container">
            <thead>
                <tr>
                    <th>News</th>
                    <th colSpan={2}></th>
                </tr>
                <tr>
                    <th scope="col">Title</th>
                    <th scope="col">Content</th>
                    <th scope="col">Date</th>
                </tr>
            </thead>
            <tbody>
                {news.map((news) => (
                    <tr className="" key={news.id}>
                        <td>{news.title}</td>
                        <td>{news.content}</td>
                        <td>{news.date}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
};

export default News;
