import React, { useEffect } from "react";
import LoginForm from "../components/LoginForm.tsx";
import GlobalStyles from "../styles/GlobalStyles";
import { login } from "../services/auth/authService.ts";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { ROUTES } from "../common/constants.ts";

const LoginPage: React.FC = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const jwtLocalStorageKey = import.meta.env.VITE_JWT_LOCAL_STORAGE_KEY;

  const handleLogin = async (email: string, password: string) => {
    console.log(`Username: ${email}, Password: ${password}`);
    await login(email, password, navigate, dispatch);
  };

  useEffect(() => {
    if (localStorage.getItem(jwtLocalStorageKey) !== null)
      navigate(ROUTES.APPOINTMENTS.LIST);
  }, []);

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
