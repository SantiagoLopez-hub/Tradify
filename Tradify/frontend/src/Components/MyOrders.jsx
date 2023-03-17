import { useEffect } from "react";
import { getMyOrders } from "./MyDetailsUtils";

const MyOrders = ({ share_id, myOrders, setMyOrders }) => {
    const [orders] = getMyOrders(share_id);

    useEffect(() => {
        if (orders.length > 0) {
            setMyOrders((previous) => [...previous, ...orders]);
        }
    }, [orders]);

    return myOrders.length === 0 ? (
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
                        <th>My Orders</th>
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
                    {myOrders.map((order) => (
                        <tr key={order.id}>
                            <td>{order.id}</td>
                            <td>{order.orderType.name}</td>
                            <td>{order.price}</td>
                            <td>{order.quantity}</td>
                            <td>{order.status.name}</td>
                            <td>
                                {new Date(order.createdAt).toLocaleString()}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default MyOrders;
