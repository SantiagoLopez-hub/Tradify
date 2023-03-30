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

export const getAllMyShares = () => {
    return ApiCall("GET", `/shares/user/${localStorage.getItem("username")}`, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("access_token")}`,
        },
    });
};

export const getMyBalance = () => {
    return ApiCall(
        "GET",
        `/users/${localStorage.getItem("username")}/balance`,
        {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("access_token")}`,
            },
        }
    );
};
