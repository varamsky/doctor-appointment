export const API_ENDPOINTS = {
  LOGIN: "/auth/login",
  SIGNUP: "/auth/signup",
  LOGOUT: "/auth/logout",
  DOCTOR: {
    CREATE: "/doctors",
    GET_ALL: "/doctors",
  },
  APPOINTMENT: {
    CREATE: "/appointments",
    GET_ALL: "/appointments",
    GET_BY_ID: "/appointments/:id",
    GET_BY_DOCTOR: "/appointments/by_doctor/doctor_id=?",
  },
  USER: {
    GET_ALL: "/users",
    GET_BY_ID: "/users/:id",
  },
};

export const ROUTES = {
  AUTH: {
    LOGIN: "/login",
    SIGNUP: "/signup",
  },
  APPOINTMENTS: { LIST: "/appointments" },
  DOCTORS: { LIST: "/doctors" },
};
