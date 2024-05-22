export type Doctor = {
  email: string;
  name: string;
  userId: string;
  username: string;
  doctorId: string;
  professionalName: string;
  appointmentSlotTime: number; // in minutes
  dayStartTime: string;
  datEndTime: string;
};

export type ErrorResponse = {
  status: number;
  message: string;
};
