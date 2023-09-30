import axios from "axios";
import { base_url } from "../base/baseUrl";
import { config } from "../base/axiosconfig";

export const getProducts = async () => {

    const response = await axios.get(`${base_url}product/?page=1&size=20`, config);
    console.log("ssss: " + response.data.data.content);
    return response.data.data.content;
};

