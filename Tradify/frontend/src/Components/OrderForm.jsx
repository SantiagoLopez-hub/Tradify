const OrderForm = () => {
    return (
        <form className="border rounded py-3">
            <div className="row">
                <div className="form-group col">
                    <label htmlFor="price">Price</label>
                    <input
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
                        type="submit"
                        className="btn btn-success w-25 mx-auto"
                    >
                        Buy
                    </button>
                </div>
                <div className="col">
                    <button
                        type="submit"
                        className="btn btn-danger w-25 mx-auto"
                    >
                        Sell
                    </button>
                </div>
            </div>
        </form>
    );
};

export default OrderForm;
