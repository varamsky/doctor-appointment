package com.example.doctor_appointment_be.common.util;

import com.example.doctor_appointment_be.doctor.Doctor;

import java.time.LocalTime;

public class TimeUtility {
    public static boolean validateStartAndEndTimes(LocalTime inputDayStartTime, LocalTime inputDayEndTime, Doctor doctor) {
        if (inputDayStartTime != null) {
            if (inputDayEndTime != null) return inputDayStartTime.isBefore(inputDayEndTime);
            else return inputDayStartTime.isBefore(doctor.getDayEndTime());
        } else return inputDayEndTime != null && inputDayEndTime.isAfter(doctor.getDayStartTime());
    }
}
