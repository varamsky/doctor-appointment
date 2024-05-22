import { API_ENDPOINTS } from "../common/constants";
import axiosInstance from "./axiosInstance";

class AppointmentService {
  static getAll = async () => {
    try {
      const response = await axiosInstance.get(
        API_ENDPOINTS.APPOINTMENT.GET_BY_DOCTOR.replace(
          "?",
          "80c5491f-d162-42a1-87a5-89094d3faf86"
        )
      );

      // console.log(`response = ${JSON.stringify(response)}`);

      // const { token } = response.data;

      return response.data;
    } catch (error) {
      throw error;
    }
  };
}

export default AppointmentService;
