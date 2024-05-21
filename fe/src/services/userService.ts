import { API_ENDPOINTS } from "../common/constants";
import axiosInstance from "./axiosInstance";

class UserService {
  static getById = async (id: string) => {
    try {
      const response = await axiosInstance.get(
        API_ENDPOINTS.USER.GET_BY_ID.replace(":id", id)
      );

      console.log(`response = ${JSON.stringify(response)}`);

      // const { token } = response.data;

      return response.data;
    } catch (error) {
      throw error;
    }
  };
}

export default UserService;
