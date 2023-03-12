import { useEffect, useRef, useState } from "react";
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
    const [err, setErr] = useState(false);
    const [orderBook, setOrderBook] = useState([]);
    const stomp = useRef(null);
    const [share, isLoading, error] = ApiCall("GET", "/shares/" + id, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("access_token")}`,
        },
    });

    const connect = () => {
        const socket = new SockJS(
            process.env.REACT_APP_DOMAIN + "/websocket-connection"
        );

        stomp.client = over(socket);

        stomp.client.connect(
            {
                Authorization: `Bearer ${localStorage.getItem("access_token")}`,
            },
            onConnected,
            onError
        );
    };

    const onError = (err) => setErr(err);

    const onConnected = () => {
        stomp.client.subscribe("/subscribe-orders/order-book", onOrderReceived);
    };

    const onOrderReceived = (order) => {
        setOrderBook((previous) => [JSON.parse(order.body), ...previous]);
    };

    const createOrder = (order_type, order) => {
        order.share = share;
        order.status = {
            id: 1,
        };
        order.orderType = {
            id: order_type,
        };

        stomp.client.send(
            "/stocks/order-book/create",
            {},
            JSON.stringify(order)
        );
    };

    const disconnect = () => {
        if (stomp.client !== null) stomp.client.disconnect();

        console.log("Disconnected");
    };

    useEffect(() => {
        connect();
    }, []);

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
                    <Orders
                        share_id={share.id}
                        orderBook={orderBook}
                        setOrderBook={setOrderBook}
                    />
                    <News share_id={share.id} />
                </>
            )}
        </div>
    );
};

export default Trade;
