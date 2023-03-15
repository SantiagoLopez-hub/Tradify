import { useEffect, useRef, useState } from "react";
import { useParams } from "react-router-dom";
import { over } from "stompjs";
import SockJS from "sockjs-client";
import ApiCall from "../Components/ApiCall";
import ApiError from "../Components/ApiError";
import News from "../Components/News";
import OrderForm from "../Components/OrderForm";
import MyOrders from "../Components/MyOrders";
import MyShares from "../Components/MyShares";
import StockGraph from "../Components/StockGraph";
import OrderBook from "../Components/OrderBook";

const Trade = () => {
    const { id } = useParams();
    const [err, setErr] = useState(false);
    const [myOrders, setMyOrders] = useState([]);
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
        setMyOrders((previous) => [JSON.parse(order.body), ...previous]);
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

                    <div id="stockGraph">
                        <StockGraph share_id={share.id} />
                    </div>

                    <div id="myOrders">
                        <MyShares share_id={share.id} />
                        <OrderForm createOrder={createOrder} />
                        <MyOrders
                            share_id={share.id}
                            myOrders={myOrders}
                            setMyOrders={setMyOrders}
                        />
                    </div>
                    <OrderBook
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
