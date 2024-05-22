package com.example.doctor_appointment_be.appointment;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class AppointmentUtility {
    public static boolean validateAppointment(LocalTime appointmentTimeToValidate, List<Appointment> appointments, Duration slotTime) {
        for (Appointment appointment : appointments) {
            LocalTime endTime = appointment.getAppointmentTime().plusMinutes(slotTime.toMinutes());

            if (appointmentTimeToValidate.isAfter(appointment.getAppointmentTime()) && appointmentTimeToValidate.isBefore(endTime))
                return false;
            if (appointmentTimeToValidate.equals(appointment.getAppointmentTime()))
                return false;
        }

        return true;
    }
}
