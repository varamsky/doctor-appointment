import { API_ENDPOINTS } from "../common/constants";
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
}

export default PublicService;