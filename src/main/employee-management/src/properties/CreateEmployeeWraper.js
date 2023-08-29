import React from "react";
import { useParams } from "react-router-dom";
import AddEmployees from "../components/employees/AddEmployees";

const CreateEmployeeWrapper = () => {
  const { id } = useParams();
  return <AddEmployees id={id} />;
};

export default CreateEmployeeWrapper;
