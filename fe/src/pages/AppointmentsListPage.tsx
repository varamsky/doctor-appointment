import { GridColDef } from "@mui/x-data-grid";
import CommonTable from "../components/CommonTable/CommonTable.tsx";
import React, { useState } from "react";
import { Typography } from "@mui/material";
import ResponsiveAppBar from "../components/ResponsiveAppBar.tsx";
import AppointmentService from "../services/appointmentService.ts";

// interface DoctorData {
//   id: number;
//   name: string;
//   professionalName?: string;
//   appointmentSlotTime?: number;
//   dayStartTime?: string;
//   dayEndTime?: string;
// }

const AppointmentsListPage: React.FC = () => {
  const [rows, setRows] = useState([]);
  const columns: GridColDef[] = [
    { field: "appointmentDate", headerName: "Date" },
    { field: "appointmentTime", headerName: "Time" },
    {
      field: "patientName",
      headerName: "Patient Name",
      minWidth: 120,
    },
    {
      field: "patientEmail",
      headerName: "Patient Email",
      minWidth: 150,
    },
    { field: "patientPhone", headerName: "Patient Phone", minWidth: 120 },
    { field: "appointmentStatus", headerName: "Status", minWidth: 150 },
  ];

  React.useEffect(() => {
    const getDoctors = async () => {
      const appointments = await AppointmentService.getAll();
      // console.log(`appointments = ${JSON.stringify(appointments)}`);

      // let doctorData: DoctorData;
      // let doctorDataList: DoctorData[];

      appointments.forEach(async (appointment: any) => {
        // doctorData = doctor;
        appointment.id = appointment.appointmentId;
        // const slotTimeDayjs = dayjs.duration(doctor.appointmentSlotTime);
        // const minutes = slotTimeDayjs.asMinutes();
        // doctor.appointmentSlotTime = minutes;

        // const user = await UserService.getById(doctor.user.userId);
        // doctor.name = user.name;

        // doctorDataList.push(doctor);
      });

      setRows(appointments);
    };
    getDoctors();
  }, []);

  return (
    <div>
      <ResponsiveAppBar />
      <Typography variant="h5">Appointments</Typography>
      <CommonTable columns={columns} rows={rows} />
    </div>
  );
};

export default AppointmentsListPage;
