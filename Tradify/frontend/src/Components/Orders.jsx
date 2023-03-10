import ApiCall from "./ApiCall";

const Orders = ({ share_id }) => {
    const [orders] = ApiCall(
        "GET",
        `/orders/${localStorage.getItem("username")}/${share_id}`,
        {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("access_token")}`,
            },
        }
    );

    return orders.length === 0 ? (
        <table className="table">
            <thead>
                <tr>
                    <th>No orders</th>
                </tr>
            </thead>
        </table>
    ) : (
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
                {orders.map((order) => (
                    <tr key={order.id}>
                        <td>{order.id}</td>
                        <td>{order.orderType.name}</td>
                        <td>{order.price}</td>
                        <td>{order.quantity}</td>
                        <td>{order.status.name}</td>
                        <td>{order.date}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
};

export default Orders;
