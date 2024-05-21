import { API_ENDPOINTS } from "../../common/constants";
import { registerDTO } from "../../common/interfaces";
import { dayJsToStringTime } from "../../common/utils";
import axiosInstance from "../axiosInstance";

export const login = async (email: string, password: string) => {
  try {
    const response = await axiosInstance.post(API_ENDPOINTS.LOGIN, {
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

export const signup = async (data: registerDTO) => {
  try {
    const response = await axiosInstance.post(API_ENDPOINTS.SIGNUP, data);

    console.log(`user response = ${JSON.stringify(response)}`);
    
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

    console.log(`doctor response = ${JSON.stringify(createDoctorResponse)}`);

      return createDoctorResponse;
    } else return response;
  } catch (error) {
    throw error;
  }
};

export const logout = async () => {
  try {
    const response = await axiosInstance.post(API_ENDPOINTS.LOGOUT);
    return response;
  } catch (error) {
    throw error;
  }
};
