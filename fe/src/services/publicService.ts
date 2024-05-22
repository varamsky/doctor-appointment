import { API_ENDPOINTS } from "../common/constants";
import { createAppointmentDTO } from "../common/interfaces";
import axiosInstance from "./axiosInstance";

class PublicService {
  static getAllDoctors = async () => {
    try {
      const response = await axiosInstance.get(
        API_ENDPOINTS.PUBLIC.GET_ALL_DOCTORS
      );

      return response.data;
    } catch (error) {
      throw error;
    }
  };

  static getAppointmentsByDoctorAndDate = async (
    doctorId: string,
    date: string
  ) => {
    try {
      const response = await axiosInstance.get(
        API_ENDPOINTS.PUBLIC.GET_APPOINTMENTS_BY_DOCTOR_AND_DATE,
        {
          params: {
            doctor_id: doctorId,
            appointment_date: date,
          },
        }
      );

      return response.data;
    } catch (error) {
      throw error;
    }
  };

  static getUserById = async (id: string) => {
    try {
      const response = await axiosInstance.get(
        API_ENDPOINTS.PUBLIC.GET_USER_BY_ID.replace(":id", id)
      );
      return response.data;
    } catch (error) {
      throw error;
    }
  };

  static createAppointment = async (data: any) => {
    try {
      const response = await axiosInstance.post(
        API_ENDPOINTS.PUBLIC.CREATE_APPOINTMENT,
        data
      );

      return response;
    } catch (error: any) {
      console.log(`ERROR ${JSON.stringify(error.response.data.error)}`);
      alert(error.response.data.error);

      throw error;
    }
  };
}

export default PublicService;
