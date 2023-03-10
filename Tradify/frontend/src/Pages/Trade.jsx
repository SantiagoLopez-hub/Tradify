import { useParams } from "react-router-dom";
import ApiCall from "../Components/ApiCall";
import ApiError from "../Components/ApiError";
import News from "../Components/News";
import Orders from "../Components/Orders";

const Trade = () => {
    const { id } = useParams();
    const [share, isLoading, error] = ApiCall("GET", "/shares/" + id, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("access_token")}`,
        },
    });

    return (
        <div>
            {error && <ApiError error={error} />}

            {isLoading ? (
                <p>Loading...</p>
            ) : (
                <>
                    <p>Trading Page for {share.name}</p>
                    <Orders share_id={share.id} />
                    <News share_id={share.id} />
                </>
            )}
        </div>
    );
};

export default Trade;
