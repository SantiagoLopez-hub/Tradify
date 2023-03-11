import { useParams } from "react-router-dom";
import ApiCall from "../Components/ApiCall";
import ApiError from "../Components/ApiError";
import News from "../Components/News";
import OrderForm from "../Components/OrderForm";
import Orders from "../Components/Orders";
import OwnwedShares from "../Components/OwnwedShares";
import PriceGraph from "../Components/PriceGraph";

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
                    <p>
                        {share.name} | {share.symbol} | {share.exchange}
                    </p>

                    <PriceGraph share_id={share.id} />
                    <OwnwedShares share_id={share.id} />
                    <OrderForm />
                    <Orders share_id={share.id} />
                    <News share_id={share.id} />
                </>
            )}
        </div>
    );
};

export default Trade;
