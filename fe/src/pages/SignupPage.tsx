import React from "react";
import GlobalStyles from "../styles/GlobalStyles.tsx";
import { signup } from "../services/auth/authService.ts";
import SignupForm from "../components/SignupForm.tsx";
import { registerDTO } from "../common/interfaces.ts";

const SignupPage: React.FC = () => {
  const handleRegister = async (data: registerDTO) => {
    const response = await signup(data);
    if (response.status === 201) console.log(`success register`);
    else console.log(`error register`);
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
        <SignupForm onSubmit={handleRegister} />
      </div>
    </GlobalStyles>
  );
};

export default SignupPage;
