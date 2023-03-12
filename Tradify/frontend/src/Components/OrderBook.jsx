import { over } from "stompjs";
import { useState } from "react";
import SockJS from "sockjs-client";
import ApiError from "./ApiError";

const OrderBook = ({ share }) => {
    const [error, setError] = useState(false);
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

    const onError = (err) => setError(err);

    const onConnected = () => {
        stompClient.subscribe("/subscribe-orders/order-book", onOrderReceived);
    };

    const onOrderReceived = (order) => {
        console.log("Message recieved:", JSON.parse(order.body));
    };

    const sendOrder = (order) => {
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

    return (
        <div>
            {error && <ApiError error={error} />}

            <h1>Order Book</h1>
            <button onClick={connect}>Connect</button>
            <button onClick={disconnect}>Disconnect</button>

            <button
                onClick={() =>
                    sendOrder({
                        quantity: 4,
                        price: 100,
                        orderType: {
                            id: 1,
                        },
                        status: {
                            id: 1,
                        },
                        date: new Date(),
                        share: share,
                        user: {
                            username: localStorage.getItem("username"),
                        },
                    })
                }
            >
                Send Message
            </button>
        </div>
    );
};

export default OrderBook;
