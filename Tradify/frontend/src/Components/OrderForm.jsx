import { useState } from "react";

const OrderForm = ({ createOrder }) => {
    const [price, setPrice] = useState(0);
    const [amount, setAmount] = useState(0);

    return (
        <form
            className="border rounded py-3"
            onSubmit={(e) => e.preventDefault()}
        >
            <div className="row">
                <div className="form-group col">
                    <label htmlFor="price">Price</label>
                    <input
                        onChange={(e) => setPrice(e.target.value)}
                        type="number"
                        min={0.01}
                        step={0.01}
                        className="form-control"
                        id="price"
                    />
                </div>
                <div className="form-group col">
                    <label htmlFor="amount">Amount</label>
                    <input
                        onChange={(e) => setAmount(e.target.value)}
                        type="number"
                        min={1}
                        step={1}
                        className="form-control"
                        id="amount"
                    />
                </div>
            </div>

            <div className="form-group row mt-2">
                <div className="col">
                    <button
                        onClick={() =>
                            createOrder(1, {
                                price: price,
                                quantity: amount,
                                user: {
                                    username: localStorage.getItem("username"),
                                },
                            })
                        }
                        type="submit"
                        className="btn btn-success w-50 mx-auto"
                    >
                        Buy
                    </button>
                </div>
                <div className="col">
                    <button
                        onClick={() =>
                            createOrder(2, {
                                price: price,
                                quantity: amount,
                                user: {
                                    username: localStorage.getItem("username"),
                                },
                            })
                        }
                        type="submit"
                        className="btn btn-danger w-50 mx-auto"
                    >
                        Sell
                    </button>
                </div>
            </div>
        </form>
    );
};

export default OrderForm;
