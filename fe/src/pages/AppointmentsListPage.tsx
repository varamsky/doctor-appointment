import { GridColDef } from "@mui/x-data-grid";
import CommonTable from "../components/CommonTable/CommonTable.tsx";
import React, { useState } from "react";
import { Typography } from "@mui/material";
import ResponsiveAppBar from "../components/ResponsiveAppBar.tsx";
import AppointmentService from "../services/appointmentService.ts";
import { useNavigate } from "react-router-dom";
import { ROUTES } from "../common/constants.ts";
import { useSelector } from "react-redux";
import { RootState } from "../redux/store.ts";

const AppointmentsListPage: React.FC = () => {
  const navigate = useNavigate();

  const currentDoctor = useSelector(
    (state: RootState) => state.doctor.currentDoctor
  );

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
  const jwtLocalStorageKey = import.meta.env.VITE_JWT_LOCAL_STORAGE_KEY;

  React.useEffect(() => {
    if (localStorage.getItem(jwtLocalStorageKey) === null)
      navigate(ROUTES.AUTH.LOGIN);
    const getDoctors = async () => {
      const appointments = await AppointmentService.getByDoctor(
        currentDoctor.doctorId
      );

      appointments.forEach(async (appointment: any) => {
        appointment.id = appointment.appointmentId;
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
