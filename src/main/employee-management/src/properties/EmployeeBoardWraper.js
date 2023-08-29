import React from "react";
import { useParams } from "react-router-dom";
import EmployeeBoard from "../components/EmployeeBoard";

const EmployeeBoardWrapper = () => {
  const { dep_id } = useParams();
  const { emp_id } = useParams();
  return <EmployeeBoard dep_id={dep_id} emp_id={emp_id} />;
};

export default EmployeeBoardWrapper;
