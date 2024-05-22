import React from "react";
import GlobalStyles from "./styles/GlobalStyles";
import LoginPage from "./pages/LoginPage.tsx";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { ROUTES } from "./common/constants.ts";
import SignupPage from "./pages/SignupPage.tsx";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import DoctorsListPage from "./pages/DoctorsListPage.tsx";
import AppointmentsListPage from "./pages/AppointmentsListPage.tsx";
import { Provider } from "react-redux";
import store from "./redux/store.ts";
import ViewDoctorPage from "./pages/ViewDoctorPage.tsx";

const App: React.FC = () => {
  return (
    <GlobalStyles>
      <Provider store={store}>
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
              <Route
                path={ROUTES.PUBLIC.DOCTORS_LIST}
                element={<DoctorsListPage />}
              />
              <Route
                path={ROUTES.PUBLIC.DOCTOR}
                element={<ViewDoctorPage />}
              />
            </Routes>
          </BrowserRouter>
        </LocalizationProvider>
      </Provider>
    </GlobalStyles>
  );
};

export default App;
