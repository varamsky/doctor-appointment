package com.example.doctor_appointment_be.doctor;

import com.example.doctor_appointment_be.user.User;
import com.example.doctor_appointment_be.common.ResourceNotFoundException;
import com.example.doctor_appointment_be.user.UserService;
import com.example.doctor_appointment_be.util.ValidateStartAndEndTimes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DoctorService {
    // TODO: should this implement an interface?

    private final DoctorRepository doctorRepository;
    private final UserService userService;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor createDoctor(CreateDoctorDTO input) {
        // TODO: use try catch here?
        User user = userService.getUserById(input.getUserId());

        // Check if a doctor with this user id is already created
        Optional<Doctor> doctorWithUserId = doctorRepository.findByUserUserId(input.getUserId());
        if (doctorWithUserId.isPresent())
            throw new DuplicateUserIdForDoctorException("Doctor already exists for this user_id - " + input.getUserId());

        Doctor doctor = new Doctor();
        doctor.setProfessionalName(input.getProfessionalName());
        doctor.setUser(user);
        doctor.setAppointmentSlotTime(input.getAppointmentSlotTime());
        doctor.setDayStartTime(input.getDayStartTime());
        doctor.setDayEndTime(input.getDayEndTime());

        return doctorRepository.save(doctor);
    }

    public Doctor getDoctorById(UUID id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (doctor.isEmpty()) throw new ResourceNotFoundException("Doctor not found for id - " + id);
        else return doctor.get();
    }

    public Doctor updateDoctorById(UUID id, UpdateDoctorRequestDTO input) {
        Doctor doctor = this.getDoctorById(id);
        if (!input.getProfessionalName().isEmpty()) doctor.setProfessionalName(input.getProfessionalName());
        if (input.getAppointmentSlotTime() != null) doctor.setAppointmentSlotTime(input.getAppointmentSlotTime());

        // Validate if the start and end times are valid or not
        boolean isValid = ValidateStartAndEndTimes.validate(input.getDayStartTime(), input.getDayEndTime(), doctor);
        if (isValid) {
            if (input.getDayStartTime() != null) doctor.setDayStartTime(input.getDayStartTime());
            if (input.getDayEndTime() != null) doctor.setDayEndTime(input.getDayEndTime());
        } else throw new InvalidStartAndEndTimeException("Invalid start or end time. Please provide valid values");

        return doctorRepository.save(doctor);
    }


    public void deleteDoctorById(UUID id) {
        Doctor doctor = this.getDoctorById(id);
        doctorRepository.deleteById(id);
    }

}
