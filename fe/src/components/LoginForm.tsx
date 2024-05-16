import * as React from "react";
import {
  TextField,
  Button,
  FormControl,
  InputAdornment,
  IconButton,
  Typography,
} from "@mui/material";
import { Visibility, VisibilityOff } from "@mui/icons-material";

interface LoginFormProps {
  onSubmit: (email: string, password: string) => void;
}

const LoginForm: React.FC<LoginFormProps> = ({ onSubmit }) => {
  const [email, setEmail] = React.useState("shubham@email.com");
  const [password, setPassword] = React.useState("password");
  const [showPassword, setShowPassword] = React.useState(false);

  const handleEmailChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  };

  const handleShowPassword = () => {
    setShowPassword(!showPassword);
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    onSubmit(email, password);
  };

  return (
    <div
      id="login-form"
      style={{ display: "flex", alignItems: "center", flexDirection: "column" }}
    >
      <Typography variant="h3">Login</Typography>
      <form onSubmit={handleSubmit}>
        <FormControl fullWidth margin="normal">
          {/* <InputLabel htmlFor="email">Email</InputLabel> */}
          <TextField
            id="email"
            label="Email"
            value={email}
            onChange={handleEmailChange}
            autoComplete="email"
            autoFocus
          />
        </FormControl>
        <FormControl fullWidth margin="normal">
          {/* <InputLabel htmlFor="password">Password</InputLabel> */}
          <TextField
            id="password"
            label="Password"
            type={showPassword ? "text" : "password"}
            value={password}
            onChange={handlePasswordChange}
            autoComplete="current-password"
            InputProps={{
              endAdornment: (
                <InputAdornment position="end">
                  <IconButton onClick={handleShowPassword}>
                    {showPassword ? <VisibilityOff /> : <Visibility />}
                  </IconButton>
                </InputAdornment>
              ),
            }}
          />
        </FormControl>
        <Button type="submit" variant="contained" color="primary" fullWidth>
          Login
        </Button>
      </form>
    </div>
  );
};

export default LoginForm;
