package com.example.doctor_appointment_be.doctor;

import com.example.doctor_appointment_be.user.User;
import com.example.doctor_appointment_be.common.ResourceNotFoundException;
import com.example.doctor_appointment_be.user.UserService;
import com.example.doctor_appointment_be.common.util.TimeUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DoctorService implements IDoctorService {
    private final DoctorRepository doctorRepository;
    private final UserService userService;

    @Override
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor save(CreateDoctorDTO input) {
        // TODO: use try catch here?
        User user = userService.getById(input.getUserId());

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

    @Override
    public Doctor getById(UUID id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (doctor.isEmpty()) throw new ResourceNotFoundException("Doctor not found for id - " + id);
        else return doctor.get();
    }

    @Override
    public Doctor updateById(UUID id, UpdateDoctorRequestDTO input) {
        Doctor doctor = this.getById(id);
        if (!input.getProfessionalName().isEmpty()) doctor.setProfessionalName(input.getProfessionalName());
        if (input.getAppointmentSlotTime() != null) doctor.setAppointmentSlotTime(input.getAppointmentSlotTime());

        // Validate if the start and end times are valid or not
        boolean isValid = TimeUtility.validateStartAndEndTimes(input.getDayStartTime(), input.getDayEndTime(), doctor);
        if (isValid) {
            if (input.getDayStartTime() != null) doctor.setDayStartTime(input.getDayStartTime());
            if (input.getDayEndTime() != null) doctor.setDayEndTime(input.getDayEndTime());
        } else throw new InvalidStartAndEndTimeException("Invalid start or end time. Please provide valid values");

        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteById(UUID id) {
        Doctor doctor = this.getById(id);
        doctorRepository.deleteById(id);
    }

}
