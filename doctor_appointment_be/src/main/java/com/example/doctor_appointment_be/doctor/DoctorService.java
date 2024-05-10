package com.example.doctor_appointment_be.doctor;

import com.example.doctor_appointment_be.auth.UserAlreadyExistsException;
import com.example.doctor_appointment_be.user.User;
import com.example.doctor_appointment_be.common.ResourceNotFoundException;
import com.example.doctor_appointment_be.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final UserService userService;

    public Doctor createDoctor(CreateDoctorDTO input) {
        // TODO: use try catch here?
        User user = userService.getUserById(input.getUserId());

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


    public void deleteDoctorById(UUID id) {
        Doctor doctor = this.getDoctorById(id);
        doctorRepository.deleteById(id);
    }

}
