import { useState } from "react";
import { useParams } from "react-router-dom";
import { over } from "stompjs";
import SockJS from "sockjs-client";
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

    const [err, setErr] = useState(false);
    let stompClient = null;

    const connect = () => {
        const socket = new SockJS(
            process.env.REACT_APP_DOMAIN + "/websocket-connection"
        );

        stompClient = over(socket);

        stompClient.connect(
            {
                Authorization: `Bearer ${localStorage.getItem("access_token")}`,
            },
            onConnected,
            onError
        );
    };

    const onError = (err) => setErr(err);

    const onConnected = () => {
        stompClient.subscribe("/subscribe-orders/order-book", onOrderReceived);
    };

    const onOrderReceived = (order) => {
        console.log("Message recieved:", JSON.parse(order.body));
    };

    const createOrder = (order_type, order) => {
        order.share = share;
        order.status = {
            id: 1,
        };
        order.orderType = {
            id: order_type,
        };

        stompClient.send(
            "/stocks/order-book/create",
            {},
            JSON.stringify(order)
        );
    };

    const disconnect = () => {
        if (stompClient !== null) stompClient.disconnect();

        console.log("Disconnected");
    };

    connect();

    return (
        <div>
            {error && <ApiError error={error} />}
            {err && <ApiError error={err} />}

            {isLoading ? (
                <p>Loading...</p>
            ) : (
                <>
                    <p>
                        {share.name} | {share.symbol} | {share.exchange}
                    </p>

                    <PriceGraph share_id={share.id} />
                    <OwnwedShares share_id={share.id} />
                    <OrderForm createOrder={createOrder} />
                    <Orders share_id={share.id} />
                    <News share_id={share.id} />
                </>
            )}
        </div>
    );
};

export default Trade;
