import React from "react";
import LoginForm from "../components/LoginForm.tsx";
import GlobalStyles from "../styles/GlobalStyles";
import { login } from "../services/auth/authService.ts";

const LoginPage: React.FC = () => {
  const handleLogin = async (email: string, password: string) => {
    console.log(`Username: ${email}, Password: ${password}`);
    const response = await login(email, password);

    if (response.status === 200) console.log(`success login`);
    else console.log(`error login`);
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
