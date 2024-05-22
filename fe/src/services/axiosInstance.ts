import axios from "axios";
import { setAuthorizationHeader } from "./auth/headerService";
import { ROUTES } from "../common/constants";

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
      window.location.pathname !== ROUTES.AUTH.LOGIN
    ) {
      const jwtLocalStorageKey = import.meta.env.VITE_JWT_LOCAL_STORAGE_KEY;

      localStorage.removeItem(jwtLocalStorageKey);
      window.location.href = ROUTES.AUTH.LOGIN;
      const response =
        error.response.data.message ?? "Session expired. Please login again.";
      alert(response);
    }
    return Promise.reject(error);
  }
);

export default axiosInstance;
