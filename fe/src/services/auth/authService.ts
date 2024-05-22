import { NavigateFunction } from "react-router-dom";
import { API_ENDPOINTS, ROUTES } from "../../common/constants";
import { registerDTO } from "../../common/interfaces";
import { dayJsToStringTime } from "../../common/utils";
import axiosInstance from "../axiosInstance";

const jwtLocalStorageKey = import.meta.env.VITE_JWT_LOCAL_STORAGE_KEY;

export const login = async (email: string, password: string) => {
  try {
    const response = await axiosInstance.post(API_ENDPOINTS.LOGIN, {
      email,
      password,
    });

    const { token } = response.data;
    localStorage.setItem(jwtLocalStorageKey, token);

    return response;
  } catch (error) {
    throw error;
  }
};

export const signup = async (data: registerDTO) => {
  try {
    const response = await axiosInstance.post(API_ENDPOINTS.SIGNUP, data);

    if (response.status === 201) {
      let doctorData: any = data;
      doctorData.user_id = response.data.userId;
      doctorData.appointment_slot_time = data.appointment_slot_time.asSeconds(); // api accepts time in seconds
      doctorData.day_start_time = dayJsToStringTime(data.day_start_time);
      doctorData.day_end_time = dayJsToStringTime(data.day_end_time);
      const createDoctorResponse = await axiosInstance.post(
        API_ENDPOINTS.DOCTOR.CREATE,
        doctorData
      );

      return createDoctorResponse;
    } else return response;
  } catch (error) {
    throw error;
  }
};

export const logout = async (navigate: NavigateFunction) => {
  localStorage.removeItem(jwtLocalStorageKey);
  navigate(ROUTES.AUTH.LOGIN);
};
