import { GridColDef } from "@mui/x-data-grid";
import CommonTable from "../components/CommonTable/CommonTable.tsx";
import React, { useState } from "react";
import DoctorService from "../services/doctorService.ts";
import dayjs from "dayjs";
import { Button, Typography } from "@mui/material";
import ResponsiveAppBar from "../components/ResponsiveAppBar.tsx";
import { useNavigate } from "react-router-dom";
import { ROUTES } from "../common/constants.ts";
import PublicService from "../services/publicService.ts";
import { useDispatch } from "react-redux";
import { addDoctors } from "../redux/slices/doctorSlice.ts";

interface DoctorData {
  id: number;
  name: string;
  professionalName: string;
  appointmentSlotTime: number;
  dayStartTime: string;
  dayEndTime: string;
}

const DoctorsListPage: React.FC = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const url = window.location.href;
  const jwtLocalStorageKey = import.meta.env.VITE_JWT_LOCAL_STORAGE_KEY;

  const [rows, setRows] = useState<DoctorData[]>([]);
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
    {
      field: "actions",
      headerName: "Actions",

      renderCell: (params) => {
        const onClick = (e: any) => {
          // e.preventDefault(); // don't select this row after clicking
          navigate(ROUTES.PUBLIC.DOCTOR.replace(":id", params.row.doctorId));
        };

        return <Button onClick={onClick}>View</Button>;
      },
    },
  ];

  React.useEffect(() => {
    const isPublic = url.includes("/public");
    if (!isPublic && localStorage.getItem(jwtLocalStorageKey) === null)
      navigate(ROUTES.AUTH.LOGIN);

    /**
     * Moving to the public page removes the data from the redux store
     * So this is a hack to remove the jwt as well to simulate a proper logout
     * Else, when we move to the authenticated appointment list page it shows an error as doctorId in redux store is null
     */
    if (isPublic) localStorage.removeItem(jwtLocalStorageKey);

    const getDoctors = async () => {
      const doctors = isPublic
        ? await PublicService.getAllDoctors()
        : await DoctorService.getAll();

      doctors.forEach(async (doctor: any) => {
        doctor.id = doctor.doctorId;
        doctor.name = doctor.user.name;
        const slotTimeDayjs = dayjs.duration(doctor.appointmentSlotTime);
        const minutes = slotTimeDayjs.asMinutes();
        doctor.appointmentSlotTime = minutes;
      });
      dispatch(addDoctors(doctors));
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
