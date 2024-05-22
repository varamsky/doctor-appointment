import React from "react";
import LoginForm from "../components/LoginForm.tsx";
import GlobalStyles from "../styles/GlobalStyles";
import { login } from "../services/auth/authService.ts";
import { useNavigate } from "react-router-dom";
import { ROUTES } from "../common/constants.ts";

const LoginPage: React.FC = () => {
  const navigate = useNavigate();

  const handleLogin = async (email: string, password: string) => {
    console.log(`Username: ${email}, Password: ${password}`);
    const response = await login(email, password);

    if (response.status === 200) {
      console.log(`success login`);
      navigate(ROUTES.APPOINTMENTS.LIST);
    } else console.log(`error login`);
  };

  return (
    <GlobalStyles>
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          minHeight: "100vh",
        }}
      >
        <LoginForm onSubmit={handleLogin} />
      </div>
    </GlobalStyles>
  );
};

export default LoginPage;
