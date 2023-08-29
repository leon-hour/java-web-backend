import React from "react";
import { useParams } from "react-router-dom";
import UpdateDepartment from "../components/departments/UpdateDepartment";

const UpdateDepartmentWrapper = () => {
  const { id } = useParams();
  return <UpdateDepartment id={id} />;
};

export default UpdateDepartmentWrapper;
