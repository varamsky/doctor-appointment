import React from "react";
import GlobalStyles from "./styles/GlobalStyles";
import LoginPage from "./pages/LoginPage.tsx";
import {
  BrowserRouter,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import { ROUTES } from "./common/constants.ts";
import SignupPage from "./pages/SignupPage.tsx";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import DoctorsListPage from "./pages/DoctorsListPage.tsx";
import AppointmentsListPage from "./pages/AppointmentsListPage.tsx";

// interface Doctor {
//   id: number;
//   name: string;
//   speciality: string;
// }

const App: React.FC = () => {
  // const [doctors, setDoctors] = React.useState<Doctor[]>([]);

  // // Replace with your logic to fetch doctor data (assuming you have a doctor API)
  // React.useEffect(() => {
  //   const fetchDoctors = async () => {
  //     const response = await fetch("http://your-api.com/doctors");
  //     const data = await response.json();
  //     setDoctors(data as Doctor[]); // Type cast to Doctor[] for type safety
  //   };

  //   fetchDoctors();
  // }, []);

  return (
    <GlobalStyles>
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Navigate to={ROUTES.AUTH.LOGIN} />} />
            <Route path={ROUTES.AUTH.LOGIN} element={<LoginPage />} />
            <Route path={ROUTES.AUTH.SIGNUP} element={<SignupPage />} />
            <Route
              path={ROUTES.APPOINTMENTS.LIST}
              element={<AppointmentsListPage />}
            />
            <Route path={ROUTES.DOCTORS.LIST} element={<DoctorsListPage />} />
          </Routes>
        </BrowserRouter>
      </LocalizationProvider>
    </GlobalStyles>
  );
};

export default App;
