import ApiCall from "./ApiCall";

export const getMyOrders = (share_id) => {
    return ApiCall(
        "GET",
        `/orders/${localStorage.getItem("username")}/${share_id}`,
        {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("access_token")}`,
            },
        }
    );
};

export const getMyShares = (share_id) => {
    return ApiCall(
        "GET",
        `/users/${localStorage.getItem("username")}/${share_id}`,
        {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("access_token")}`,
            },
        }
    );
};
