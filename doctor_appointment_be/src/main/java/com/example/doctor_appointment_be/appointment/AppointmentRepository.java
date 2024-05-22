package com.example.doctor_appointment_be.appointment;

import com.example.doctor_appointment_be.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> getAppointmentsByDoctorAndAppointmentDate(Doctor doctor, LocalDate appointmentDate);
    List<Appointment> getAppointmentsByDoctor(Doctor doctor);

//    @Query("SELECT\n" +
//            "appointmentDate as date,\n" +
//            "  COUNT(*) AS numberOfAppointments,\n" +
//            "  SUM(CASE WHEN a.appointmentStatus = 2 THEN 1 ELSE 0 END) AS numberOfCancelledAppointments,\n" +
//            "  SUM(CASE WHEN a.appointmentStatus = 1 THEN 1 ELSE 0 END) AS numberOfClosedAppointments\n" +
//            "FROM Appointment a\n" +
//            "WHERE YEAR(a.appointmentDate) = ?1\n" +
//            "  AND MONTH(a.appointmentDate) = ?2\n" +
//            "GROUP BY a.appointmentDate;")
//    List<GetSummaryReportResponseDTO> findByAppointmentDateMonth(int year, int month);



//    @Query("SELECT NEW com.example.doctor_appointment_be.appointment.GetSummaryReportResponseDTO(a.appointmentDate, COUNT(*), SUM(CASE WHEN a.appointmentStatus = 2 THEN 1 ELSE 0 END), SUM(CASE WHEN a.appointmentStatus = 1 THEN 1 ELSE 0 END))" +
//            " FROM Appointment a" +
//            " WHERE YEAR(a.appointmentDate) = ?1" +
//            "  AND MONTH(a.appointmentDate) = ?2" +
//            " GROUP BY a.appointmentDate;")
//    List<GetSummaryReportResponseDTO> findByAppointmentDateMonth(int year, int month);
}
//jdbc template

//native sql -
//
//query dsl - complex