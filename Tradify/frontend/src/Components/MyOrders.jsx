import { useEffect } from "react";
import ApiCall from "./ApiCall";

const MyOrders = ({ share_id, orderBook, setOrderBook }) => {
    const [orders] = ApiCall(
        "GET",
        `/orders/${localStorage.getItem("username")}/${share_id}`,
        {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("access_token")}`,
            },
        }
    );

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
        <div className="overflow-auto" id="myOrdersTable">
            <table className="table">
                <thead>
                    <tr>
                        <th>Orders</th>
                        <th colSpan={5}></th>
                    </tr>
                    <tr>
                        <th scope="col">Reference Number</th>
                        <th scope="col">Type</th>
                        <th scope="col">Price</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Status</th>
                        <th scope="col">Date</th>
                    </tr>
                </thead>
                <tbody>
                    {orderBook.map((order) => (
                        <tr key={order.id}>
                            <td>{order.id}</td>
                            <td>{order.orderType.name}</td>
                            <td>{order.price}</td>
                            <td>{order.quantity}</td>
                            <td>{order.status.name}</td>
                            <td>{new Date(order.date).toLocaleString()}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default MyOrders;
