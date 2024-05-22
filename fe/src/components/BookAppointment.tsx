import {
  Button,
  FormControl,
  Grid,
  List,
  ListItem,
  TextField,
} from "@mui/material";
import { DatePicker, TimePicker } from "@mui/x-date-pickers";
import dayjs, { Dayjs } from "dayjs";
import { useState } from "react";
import PublicService from "../services/publicService";
import { createAppointmentDTO } from "../common/interfaces";
import { dayJsToStringTime } from "../common/utils";

interface Props {
  slotTime: number;
  doctorId: string;
}

const BookAppointment = (props: Props) => {
  const [startTime, setStartTime] = useState<Dayjs>();
  const [endTime, setEndTime] = useState<Dayjs>();
  const [date, setDate] = useState(dayjs());

  const [name, setName] = useState<string>("Public User");
  const [email, setEmail] = useState<string>("public_user@email.com");
  const [phone, setPhone] = useState<string>("9876543210");

  const handleDayStartTimeChange = (newValue: Dayjs | null) => {
    newValue = dayjs(newValue);
    setStartTime(newValue);
    const newEndTime = dayjs(newValue).add(props.slotTime, "minute");
    setEndTime(newEndTime);
  };

  const handleSubmit = async (e: { preventDefault: () => void }) => {
    e.preventDefault();
    console.log(`handleSubmit`);

    const data = {
      patient_name: name,
      patient_email: email,
      patient_phone: phone,
      doctor_id: props.doctorId,
      appointment_time: startTime?.format("HH:mm") ?? "",
      appointment_date: date.format("YYYY-MM-DD"),
      appointment_status: "OPEN",
    };
    const response = await PublicService.createAppointment(data);
    if (response.status === 201) {
      console.log(`SUCCESS`);
      alert("SUCCESS");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <Grid container spacing={2} direction={"row"}>
        <Grid item xs={12} sm={6}>
          <List dense>
            <ListItem>
              <FormControl margin="normal">
                <TimePicker
                  label="Start Time"
                  value={startTime}
                  disablePast
                  onChange={handleDayStartTimeChange}
                />
              </FormControl>
            </ListItem>
            <ListItem>
              <FormControl margin="normal">
                <DatePicker label="Date" value={date} disabled />
              </FormControl>{" "}
            </ListItem>
          </List>
        </Grid>

        <Grid item xs={12} sm={6}>
          <List dense>
            <ListItem>
              <FormControl size="small" margin="normal">
                <TimePicker label="End Time" value={endTime} readOnly />
              </FormControl>
            </ListItem>
            <ListItem>
              <FormControl margin="normal">
                <TextField label="Name" value={name} />
              </FormControl>
            </ListItem>
          </List>
        </Grid>

        <Grid item xs={12} sm={6}>
          <List dense>
            <ListItem>
              <FormControl margin="normal">
                <TextField type="email" value={email} label="Email" />
              </FormControl>
            </ListItem>
            <ListItem></ListItem>
          </List>
        </Grid>

        <Grid item xs={12} sm={6}>
          <List dense>
            <ListItem>
              <FormControl margin="normal">
                <TextField value={phone} label="Phone" />
              </FormControl>
            </ListItem>
            <ListItem>
              <FormControl size="small" margin="normal">
                <Button
                  fullWidth
                  type="submit"
                  variant="contained"
                  color="primary"
                >
                  Book Appointment
                </Button>
              </FormControl>
            </ListItem>
          </List>
        </Grid>
      </Grid>
    </form>
  );
};

export default BookAppointment;
