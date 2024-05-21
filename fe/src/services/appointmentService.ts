import { API_ENDPOINTS } from "../common/constants";
import axiosInstance from "./axiosInstance";

class AppointmentService {
  static getAll = async () => {
    try {
      const response = await axiosInstance.get(
        API_ENDPOINTS.APPOINTMENT.GET_ALL
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
