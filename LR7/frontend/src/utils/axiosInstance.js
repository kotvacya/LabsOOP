const { default: axios } = require("axios");

const instance = axios.create({
    baseURL: "/api"
});

instance.interceptors.response.use(
    function (response) {
        return response;
    },
    function (error) {
        console.log(error);
        
        return Promise.reject(error);
    }
);

export default instance;
