import { Dayjs } from "dayjs";

export const dayJsToStringTime = (time: Dayjs) => {
  return `${time.hour().toString().padStart(2, "0")}:${time
    .minute()
    .toString()
    .padStart(2, "0")}`;
};
