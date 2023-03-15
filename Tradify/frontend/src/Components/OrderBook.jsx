import { useEffect } from "react";
import ApiCall from "./ApiCall";

const OrderBook = ({ share_id, orderBook, setOrderBook }) => {
    const [orders] = ApiCall("GET", `/orders/${share_id}`, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("access_token")}`,
        },
    });

    console.log(orders);

    useEffect(() => {
        if (orders.length > 0) {
            setOrderBook((previous) => [...previous, ...orders]);
        }
    }, [orders]);

    return orderBook.length === 0 ? (
        <table className="table">
            <thead>
                <tr>
                    <th>No orders</th>
                </tr>
            </thead>
        </table>
    ) : (
        <div className="overflow-auto" id="orderBook">
            <table className="table text-center" id="buy">
                <thead>
                    <tr>
                        <th colSpan={2}>Order Book</th>
                    </tr>
                    <tr>
                        <th colSpan={2}>Buys</th>
                    </tr>
                    <tr>
                        <th scope="col">Price</th>
                        <th scope="col">Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    {orderBook.map((order) => {
                        if (order.orderType.id === 1) {
                            return (
                                <tr key={order.id}>
                                    <td>{order.id}</td>
                                    <td>{order.price}</td>
                                </tr>
                            );
                        }
                    })}
                </tbody>
            </table>

            <table className="table text-center" id="sell">
                <thead>
                    <tr>
                        <th colSpan={2} className="invisible">
                            Order book
                        </th>
                    </tr>
                    <tr>
                        <th colSpan={2}>Sells</th>
                    </tr>
                    <tr>
                        <th scope="col">Price</th>
                        <th scope="col">Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    {orderBook.map((order) => {
                        if (order.orderType.id === 2) {
                            return (
                                <tr key={order.id}>
                                    <td>{order.id}</td>
                                    <td>{order.price}</td>
                                </tr>
                            );
                        }
                    })}
                </tbody>
            </table>
        </div>
    );
};

export default OrderBook;
