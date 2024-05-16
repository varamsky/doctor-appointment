import { createTheme, ThemeProvider } from "@mui/material/styles";

const theme = createTheme({
  palette: {
    mode: "light", // Set the mode to 'light' for a light theme
    primary: {
      main: "#007bff", // Adjust to your desired primary color
    },
    secondary: {
      main: "#ffc107", // Adjust to your desired secondary color
    },
    background: {
      default: "#fff", // Set the default background color to white
    },
    text: {
      primary: "#000", // Set primary text color to black
      secondary: "#757575", // Set secondary text color to a light gray
    },
  },
});

interface GlobalStylesProps {
  children: React.ReactNode;
}

const GlobalStyles: React.FC<GlobalStylesProps> = ({ children }) => {
  return <ThemeProvider theme={theme}>{children}</ThemeProvider>;
};

export default GlobalStyles;
