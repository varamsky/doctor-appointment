import React from "react";
import LoginForm from "../components/LoginForm.tsx";
import GlobalStyles from "../styles/GlobalStyles";
import { login } from "../services/auth/authService.ts";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";

const LoginPage: React.FC = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleLogin = async (email: string, password: string) => {
    console.log(`Username: ${email}, Password: ${password}`);
    const response = await login(email, password, navigate, dispatch);
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
