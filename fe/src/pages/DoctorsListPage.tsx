import { GridColDef } from "@mui/x-data-grid";
import CommonTable from "../components/CommonTable/CommonTable.tsx";
import React, { useState } from "react";
import DoctorService from "../services/doctorService.ts";
import dayjs from "dayjs";
import UserService from "../services/userService.ts";
import { Typography } from "@mui/material";
import ResponsiveAppBar from "../components/ResponsiveAppBar.tsx";

interface DoctorData {
  id: number;
  name: string;
  professionalName?: string;
  appointmentSlotTime?: number;
  dayStartTime?: string;
  dayEndTime?: string;
}

const DoctorsListPage: React.FC = () => {
  const [rows, setRows] = useState<DoctorData[]>([]);
  // const [doctorsList, setDoctorsList] = useState([]);
  const columns: GridColDef[] = [
    { field: "name", headerName: "Name", minWidth: 150 },
    { field: "professionalName", headerName: "Designation" },
    {
      field: "appointmentSlotTime",
      headerName: "Slot Time(mins)",
      minWidth: 120,
    },
    { field: "dayStartTime", headerName: "Start Time" },
    { field: "dayEndTime", headerName: "End Time" },
  ];

  React.useEffect(() => {
    const getDoctors = async () => {
      const doctors = await DoctorService.getAll();
      // console.log(`doctors = ${JSON.stringify(doctors)}`);

      // let doctorData: DoctorData;
      // let doctorDataList: DoctorData[];

      doctors.forEach(async (doctor: any) => {
        // doctorData = doctor;
        doctor.id = doctor.doctorId;
        const slotTimeDayjs = dayjs.duration(doctor.appointmentSlotTime);
        const minutes = slotTimeDayjs.asMinutes();
        doctor.appointmentSlotTime = minutes;

        const user = await UserService.getById(doctor.user.userId);
        doctor.name = user.name;

        // doctorDataList.push(doctor);
      });

      setRows(doctors);
    };
    getDoctors();
  }, []);

  return (
    <div>
      <ResponsiveAppBar />
      <Typography variant="h5">Doctors</Typography>
      <CommonTable columns={columns} rows={rows} />
    </div>
  );
};

export default DoctorsListPage;
