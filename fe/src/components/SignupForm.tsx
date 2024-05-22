import * as React from "react";
import { TextField, Button, FormControl, Typography } from "@mui/material";
import { TimePicker } from "@mui/x-date-pickers/TimePicker";
import { ROUTES } from "../common/constants";
import { Link } from "react-router-dom";
import dayjs, { Dayjs } from "dayjs";
import duration, { Duration } from "dayjs/plugin/duration";
import { registerDTO } from "../common/interfaces";

dayjs.extend(duration);

interface SignupFormProps {
  onSubmit: (data: registerDTO) => void;
}

const defaultTimeSlotValue = "30";
const defaultStartTime = dayjs("09:00:00", "HH:mm:ss");
const defaultEndTime = dayjs("17:00:00", "HH:mm:ss");

const SignupForm: React.FC<SignupFormProps> = ({ onSubmit }) => {
  const [name, setName] = React.useState("Shubham Kumar");
  const [email, setEmail] = React.useState("shubham1@email.com");
  const [password, setPassword] = React.useState("password");
  const [professionalName, setProfessionalName] = React.useState("Physician");
  const [appointmentSlotTime, setAppointmentSlotTime] =
    React.useState<Duration>(dayjs.duration({ minutes: 30 }));
  const [dayStartTime, setDayStartTime] =
    React.useState<Dayjs>(defaultStartTime);
  const [dayEndTime, setDayEndTime] = React.useState<Dayjs>(defaultEndTime);

  const handleNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setName(event.target.value);
  };

  const handleEmailChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  };

  const handleProfessionalNameChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setProfessionalName(event.target.value);
  };

  const handleAppointmentSlotTimeChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    const newValue = event.target.value ?? defaultTimeSlotValue;
    const newDuration: Duration = dayjs.duration({
      minutes: Number(newValue),
    });
    setAppointmentSlotTime(newDuration);
  };

  const handleDayStartTimeChange = (newValue: Dayjs | null) => {
    setDayStartTime(newValue ?? defaultStartTime);
  };

  const handleDayEndTimeChange = (newValue: Dayjs | null) => {
    setDayEndTime(newValue ?? defaultEndTime);
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    onSubmit({
      name,
      email,
      password,
      professional_name: professionalName,
      appointment_slot_time: appointmentSlotTime,
      day_start_time: dayStartTime,
      day_end_time: dayEndTime,
    });
  };

  return (
    <div
      id="signup-form"
      style={{
        display: "flex",
        alignItems: "center",
        flexDirection: "column",
        maxWidth: "50vw",
      }}
    >
      <Typography variant="h3">Signup</Typography>
      <form onSubmit={handleSubmit}>
        <FormControl fullWidth margin="normal">
          <TextField
            id="name"
            label="Name"
            value={name}
            onChange={handleNameChange}
          />
        </FormControl>
        <FormControl fullWidth margin="normal">
          <TextField
            id="email"
            label="Email"
            type="email"
            value={email}
            onChange={handleEmailChange}
          />
        </FormControl>
        <FormControl fullWidth margin="normal">
          <TextField
            id="password"
            label="Password"
            type="password"
            value={password}
            onChange={handlePasswordChange}
          />
        </FormControl>
        <FormControl fullWidth margin="normal">
          <TextField
            id="professionalName"
            label="Professional Name"
            value={professionalName}
            onChange={handleProfessionalNameChange}
          />
        </FormControl>
        <FormControl fullWidth margin="normal">
          <TextField
            id="appointmentSlotTime"
            label="Appointment Slot Time (in minutes)"
            value={appointmentSlotTime?.asMinutes()}
            onChange={handleAppointmentSlotTimeChange}
          />
        </FormControl>
        <FormControl fullWidth margin="normal">
          <TimePicker
            // id="dayEndTime"
            label="Day Start Time"
            value={dayStartTime}
            onChange={handleDayStartTimeChange}
          />
        </FormControl>
        <FormControl fullWidth margin="normal">
          <TimePicker
            // id="dayEndTime"
            label="Day End Time"
            value={dayEndTime}
            onChange={handleDayEndTimeChange}
          />
        </FormControl>
        <Button type="submit" variant="contained" color="primary" fullWidth>
          Signup
        </Button>
      </form>
      <Typography sx={{ mt: 2 }}>
        Already a registered doctor? <Link to={ROUTES.AUTH.LOGIN}>Login</Link>
      </Typography>
    </div>
  );
};

export default SignupForm;
