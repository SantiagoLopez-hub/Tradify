import ApiCall from "./ApiCall";

export const getTradingHistory = (share_id) => {
    return ApiCall("GET", `/shares/${share_id}/trading-history`, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("access_token")}`,
        },
    });
};

export const getOrderBook = (share_id) => {
    return ApiCall("GET", `/orders/${share_id}`, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("access_token")}`,
        },
    });
};
