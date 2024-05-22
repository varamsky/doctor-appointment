import { API_ENDPOINTS } from "../common/constants";
import axiosInstance from "./axiosInstance";

class AppointmentService {
  static getByDoctor = async (doctorId: string | null) => {
    try {
      if (doctorId === null) throw new Error("Doctor id is null");
      else {
        const response = await axiosInstance.get(
          API_ENDPOINTS.APPOINTMENT.GET_BY_DOCTOR.replace(
            "{doctor_id}",
            doctorId
          )
        );
        return response.data;
      }
    } catch (error) {
      throw error;
    }
  };
}

export default AppointmentService;
