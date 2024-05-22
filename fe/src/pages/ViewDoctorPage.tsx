import {
  Box,
  Grid,
  List,
  ListItem,
  ListItemText,
  Typography,
} from "@mui/material";
import { useEffect, useState } from "react";
import { RootState } from "../redux/store";
import { useSelector } from "react-redux";
import { Doctor } from "../common/types";
import ResponsiveAppBar from "../components/ResponsiveAppBar";
import dayjs from "dayjs";
import PublicService from "../services/publicService";
import CommonTable from "../components/CommonTable/CommonTable";
import { GridColDef } from "@mui/x-data-grid";
import BookAppointment from "../components/BookAppointment";

const ViewDoctorPage: React.FC = () => {
  const [doctor, setDoctor] = useState<Doctor>();
  const doctors = useSelector((state: RootState) => state.doctor.doctors);

  const [rows, setRows] = useState([]);
  const columns: GridColDef[] = [
    { field: "appointmentDate", headerName: "Date" },
    { field: "appointmentTime", headerName: "Start Time" },
    { field: "appointmentEndTime", headerName: "End Time" },
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

  const url = window.location.href;
  const list = url.split("/");
  const length = list.length;
  const doctorId = list[length - 1];

  useEffect(() => {
    const getAppointments = async () => {
      const doc = doctors.find((doctor) => doctor["doctorId"] === doctorId);

      if (doc) {
        setDoctor(doc);

        const date = dayjs(new Date()).format("YYYY-MM-DD");

        const appointments = await PublicService.getAppointmentsByDoctorAndDate(
          doc["doctorId"],
          date
        );

        console.log(`appointments = ${JSON.stringify(appointments)}`);
        appointments.forEach(async (appointment: any) => {
          appointment.id = appointment.appointmentId;

          const endTime = dayjs(appointment.appointmentTime, "HH:mm:ss")
            .add(doc["appointmentSlotTime"], "minute")
            .format("HH:mm:ss");
          appointment.appointmentEndTime = endTime;
        });

        setRows(appointments);
      }
    };

    getAppointments();
  }, []);

  if (!doctor) return null;
  return (
    <div>
      <ResponsiveAppBar />
      <Typography variant="h6" marginLeft={2}>
        Basic Information
      </Typography>
      <Grid container spacing={2}>
        <Grid item xs={12} sm={6}>
          <List dense>
            <ListItem>
              <ListItemText primary="Name" secondary={doctor.user.name} />
            </ListItem>
            <ListItem>
              <ListItemText primary="Email" secondary={doctor.user.email} />
            </ListItem>
            <ListItem>
              <ListItemText
                primary="Designation"
                secondary={doctor.professionalName}
              />
            </ListItem>
          </List>
        </Grid>
        <Grid item xs={12} sm={6}>
          <List dense>
            <ListItem>
              <ListItemText
                primary="Slot Time"
                secondary={`${doctor.appointmentSlotTime} minutes`}
              />
            </ListItem>
            <ListItem>
              <ListItemText
                primary="Start Time"
                secondary={doctor.dayStartTime}
              />
            </ListItem>
            <ListItem>
              <ListItemText primary="End Time" secondary={doctor.dayEndTime} />
            </ListItem>
          </List>
        </Grid>
      </Grid>
      <BookAppointment
        slotTime={doctor.appointmentSlotTime}
        doctorId={doctor.doctorId}
      />
      <Box sx={{ m: 2 }}>
        <Typography variant="h5" sx={{ mb: 2 }}>
          Appointments
        </Typography>
        <CommonTable columns={columns} rows={rows} />
      </Box>
    </div>
  );
};
export default ViewDoctorPage;
