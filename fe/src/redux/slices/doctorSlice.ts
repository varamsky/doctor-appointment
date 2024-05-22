import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  currentDoctor: {
    email: null,
    name: null,
    userId: null,
    username: null,
    doctorId: null,
    professionalName: null,
    appointmentSlotTime: null,
    dayStartTime: null,
    datEndTime: null,
  },
  doctors: [],
};

const doctorSlice = createSlice({
  name: "doctor",
  initialState,
  reducers: {
    setDoctor(state, action) {
      state.currentDoctor = action.payload;
    },
    addDoctors(state, action) {
      state.doctors = action.payload;
    },
  },
});

export const { setDoctor, addDoctors } = doctorSlice.actions;

export default doctorSlice.reducer;
