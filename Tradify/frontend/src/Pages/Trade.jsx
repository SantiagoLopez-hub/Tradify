import { useParams } from "react-router-dom";
import ApiCall from "../Components/ApiCall";

const Trade = () => {
    const { id } = useParams();
    const [share, isLoading, error] = ApiCall("GET", "/shares/" + id, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("access_token")}`,
        },
    });

    return (
        <div>
            <p>Trading Page for {share.name}</p>
        </div>
    );
};

export default Trade;
