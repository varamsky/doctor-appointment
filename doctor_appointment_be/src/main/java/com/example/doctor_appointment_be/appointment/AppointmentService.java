package com.example.doctor_appointment_be.appointment;

import com.example.doctor_appointment_be.common.AppointmentStatusEnum;
import com.example.doctor_appointment_be.common.ResourceNotFoundException;
import com.example.doctor_appointment_be.doctor.Doctor;
import com.example.doctor_appointment_be.doctor.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(UUID id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isEmpty()) throw new ResourceNotFoundException("Appointment not found for id - " + id);
        else return appointment.get();
    }

    public List<Appointment> getAppointmentsByDoctorAndAppointmentDate(GetAppointmentsByDoctorAndAppointmentDateDTO input) {
        Doctor doctor = doctorService.getDoctorById(input.getDoctorId());

        return appointmentRepository.getAppointmentsByDoctorAndAppointmentDate(doctor, input.getAppointmentDate());
    }

    public Appointment createAppointment(CreateAppointmentDTO input) {
        Doctor doctor = doctorService.getDoctorById(input.getDoctorId());

        List<Appointment> appointmentsWithSameDoctorAndDate = appointmentRepository.getAppointmentsByDoctorAndAppointmentDate(doctor, input.getAppointmentDate());

        if (!AppointmentUtility.validateAppointment(input.getAppointmentTime(), appointmentsWithSameDoctorAndDate, doctor.getAppointmentSlotTime()))
            throw new AppointmentSlotNotAvailableException("Slot not available");

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(input.getAppointmentDate());
        appointment.setAppointmentTime(input.getAppointmentTime());
        appointment.setDoctor(doctor);
        appointment.setPatientName(input.getPatientName());
        appointment.setPatientEmail(input.getPatientEmail());
        appointment.setPatientPhone(input.getPatientPhone());
        appointment.setAppointmentStatus(input.getAppointmentStatus());

        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(UUID id, UpdateAppointmentDTO input) {
        Appointment appointment = getAppointmentById(id);

        if (appointment.getAppointmentStatus() != AppointmentStatusEnum.OPEN)
            throw new InvalidAppointmentStatusUpdateException("This appointment is already in " + appointment.getAppointmentStatus() + " state. It cannot be modified.");

        if (input.getAppointmentStatus() == AppointmentStatusEnum.CLOSED || input.getAppointmentStatus() == AppointmentStatusEnum.CANCELLED)
            appointment.setAppointmentStatus(input.getAppointmentStatus());
        else
            throw new InvalidAppointmentStatusUpdateException("Open Appointments can only be modified to CLOSED or CANCELLED");

        return appointmentRepository.save(appointment);
    }

    public List<GetSummaryReportResponseDTO> getSummaryReport(GetSummaryReportRequestDTO input) {
        if (input.getYear() > LocalDate.now().getYear() || (input.getMonth() < 0 || input.getMonth() > 12))
            throw new InvalidMonthOrYearException("Please provide valid month and year");

//        List<GetSummaryReportResponseDTO> appointments = appointmentRepository.findByAppointmentDateMonth(input.getYear(), input.getMonth());
        List<GetSummaryReportResponseDTO> appointments = new ArrayList<>();

//        int totalAppointments = 0;
//        int closedAppointments = 0;
//        int cancelledAppointments = 0;
//
//        for (Appointment appointment : appointments) {
//            totalAppointments++;
//            if (appointment.getAppointmentStatus() == AppointmentStatusEnum.CLOSED) {
//                closedAppointments++;
//            } else if (appointment.getAppointmentStatus() == AppointmentStatusEnum.CANCELLED) {
//                cancelledAppointments++;
//            }
//        }

        return appointments;
    }
}
