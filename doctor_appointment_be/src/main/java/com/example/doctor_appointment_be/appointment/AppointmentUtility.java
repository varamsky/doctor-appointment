package com.example.doctor_appointment_be.appointment;

import com.example.doctor_appointment_be.doctor.Doctor;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class AppointmentUtility {
    public static boolean validateAppointment(LocalTime appointmentTimeToValidate, List<Appointment> appointments, Duration slotTime, LocalTime doctorDayStartTime, LocalTime doctorDayEndTime) {
        LocalTime appointmentEndTimeToValidate = appointmentTimeToValidate.plusMinutes(slotTime.toMinutes());
        if (appointmentTimeToValidate.isBefore(doctorDayStartTime) || appointmentEndTimeToValidate.isAfter(doctorDayEndTime))
            return false;
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
