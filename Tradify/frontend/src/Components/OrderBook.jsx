import { useEffect } from "react";
import { getOrderBook } from "./SystemUtils";

const OrderBook = ({ share_id, orderBook, setOrderBook }) => {
    const [orders] = getOrderBook(share_id);

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
                                    <td>{order.price}</td>
                                    <td>{order.quantity}</td>
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
