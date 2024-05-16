// src/components/Sidebar.tsx
import * as React from "react";
import {
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  Divider,
} from "@mui/material";
import {
  Dashboard as DashboardIcon,
  People as PeopleIcon,
  Event as EventIcon,
} from "@mui/icons-material";

interface SidebarProps {}

const Sidebar: React.FC<SidebarProps> = () => {
  return (
    <div style={{ width: 250, padding: "1rem" }}>
      <List>
        <ListItem button>
          <ListItemIcon>
            <DashboardIcon />
          </ListItemIcon>
          <ListItemText primary="Dashboard" />
        </ListItem>
        <ListItem button>
          <ListItemIcon>
            <PeopleIcon />
          </ListItemIcon>
          <ListItemText primary="Doctors" />
        </ListItem>
        <ListItem button>
          <ListItemIcon>
            <EventIcon />
          </ListItemIcon>
          <ListItemText primary="Appointments" />
        </ListItem>
      </List>
      <Divider />
    </div>
  );
};

export default Sidebar;
