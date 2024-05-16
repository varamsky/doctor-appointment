// src/components/DoctorsTable.tsx
import * as React from 'react';
import { TableContainer, Table, TableHead, TableBody, TableRow, TableCell } from '@mui/material';

interface Doctor {
  id: number;
  name: string;
  speciality: string;
}

interface DoctorsTableProps {
  doctors: Doctor[];
}

const DoctorsTable: React.FC<DoctorsTableProps> = ({ doctors }) => {
  return (
    <TableContainer>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>ID</TableCell>
            <TableCell>Name</TableCell>
            <TableCell>Speciality</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {doctors.map((doctor) => (
            <TableRow key={doctor.id}>
              <TableCell>{doctor.id}</TableCell>
              <TableCell>{doctor.name}</TableCell>
              <TableCell>{doctor.speciality}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default DoctorsTable;
