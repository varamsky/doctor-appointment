import React from "react";
import Sidebar from "./components/Sidebar";
import DoctorsTable from "./components/DoctorTable.tsx";
import GlobalStyles from "./styles/GlobalStyles";
import LoginPage from "./pages/LoginPage.tsx";

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
      <div>
        <LoginPage />
        {/* <Sidebar />
        <main style={{ flex: 1, padding: "1rem" }}>
          <DoctorsTable doctors={doctors} />
        </main> */}
      </div>
    </GlobalStyles>
  );
};

export default App;
