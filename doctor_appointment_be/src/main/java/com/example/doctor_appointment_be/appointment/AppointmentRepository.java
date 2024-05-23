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

    @Query(value = """
            SELECT
                appointment_date as appointmentDate,
                COUNT(*) AS numberOfAppointments,
                SUM(CASE WHEN a.appointment_status = 'CANCELLED' THEN 1 ELSE 0 END) AS numberOfCancelledAppointments,
                SUM(CASE WHEN a.appointment_status = 'CLOSED' THEN 1 ELSE 0 END) AS numberOfClosedAppointments
            FROM appointments AS a
                WHERE YEAR(a.appointment_date) = ?1
                AND MONTH(a.appointment_date) = ?2
            GROUP BY a.appointment_date
            """, nativeQuery = true)
    List<ISummaryReport> getSummaryReport(int year, int month); // this uses "interface based projection"

    @Query(value = """
            SELECT
                appointment_date as appointmentDate,
                patient_name as patientName,
                appointment_status as appointmentStatus
            FROM appointments
                WHERE YEAR(appointment_date) = ?1
                AND MONTH(appointment_date) = ?2
            """, nativeQuery = true)
    List<IDetailReport> getDetailReport(int year, int month); // this uses "interface based projection"
}

//jdbc template

//native sql -
//
//query dsl - complex