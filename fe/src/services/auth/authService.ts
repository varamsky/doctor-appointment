import { ROUTES } from "../../common/constants";
import axiosInstance from "../axiosInstance";

export const login = async (email: string, password: string) => {
  try {
    const response = await axiosInstance.post(ROUTES.login, {
      email,
      password,
    });

    const { token } = response.data;
    localStorage.setItem("jwt", token);

    return response;
  } catch (error) {
    throw error;
  }
};

export const logout = async () => {
  try {
    const response = await axiosInstance.post("/logout");
    return response;
  } catch (error) {
    throw error;
  }
};
