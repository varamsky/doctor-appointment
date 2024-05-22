import { configureStore } from "@reduxjs/toolkit";

import doctorSlice from "./slices/doctorSlice";

const store = configureStore({
  reducer: {
    doctor: doctorSlice, // Add your slices here
  },
});

// Infer the `RootState` and `AppDispatch` types
export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

export default store;
