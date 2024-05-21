// src/components/commonTable.tsx
import * as React from "react";
import { DataGrid, GridColDef, GridRowsProp } from "@mui/x-data-grid";
import "./CommonTable.css";

// interface CommonTableProps<T> {
interface CommonTableProps {
  // columns: {
  //   field: keyof T;
  //   headerName: string;
  //   width?: number;
  //   type: any;
  // }[];
  columns: GridColDef[];
  // rows: T[];
  rows: GridRowsProp;
}

const CommonTable: React.FC<CommonTableProps> = ({ columns, rows }) => {
  return (
    <div style={{ height: 400, width: "100%" }}>
      <DataGrid
        rows={rows}
        columns={columns}
        // pageSize={5}
        // rowsPerPageOptions={[5, 10, 25]}
      />
    </div>
  );
};

export default CommonTable;
