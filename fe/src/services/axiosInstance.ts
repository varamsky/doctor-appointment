import axios from "axios";
import { setAuthorizationHeader } from "./auth/headerService";

const apiUrl = import.meta.env.VITE_API_URL;

const axiosInstance = axios.create({
  baseURL: apiUrl,
  timeout: 10000,
});

axiosInstance.interceptors.request.use(
  (config) => {
    return setAuthorizationHeader(config);
  },
  (error) => {
    return Promise.reject(error);
  }
);

axiosInstance.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (
      error.response.status === 401 &&
      window.location.pathname !== "/login"
    ) {
      localStorage.removeItem("jwt");
      window.location.href = "/login";
      const response =
        error.response.data.message ?? "Session expired. Please login again.";
      alert(response);
    }
    return Promise.reject(error);
  }
);

export default axiosInstance;
