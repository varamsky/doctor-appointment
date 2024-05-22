export type Doctor = {
  user: {
    email: string;
    name: string;
    username: string;
    userId: string;
  };
  doctorId: string;
  professionalName: string;
  appointmentSlotTime: number; // in minutes
  dayStartTime: string;
  dayEndTime: string;
};

export type ErrorResponse = {
  status: number;
  message: string;
};
