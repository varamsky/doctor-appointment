import { Dayjs } from "dayjs";
import { Duration } from "dayjs/plugin/duration";

export interface registerDTO {
  name: string;
  email: string;
  password: string;
  professional_name: string;
  appointment_slot_time: Duration;
  day_start_time: Dayjs;
  day_end_time: Dayjs;
}

export interface createAppointmentDTO {
  appointmentDate: string;
  appointmentTime: string;
  doctorId: string;
  patientName: string;
  patientEmail: string;
  patientPhone: string;
  appointmentStatus: string;
}
