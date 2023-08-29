import React from "react";
import { useParams } from "react-router-dom";
import UpdateEmployee from "../components/employees/UpdateEmployee";

const UpdateEmployeeWrapper = () => {
  const { dep_id, id } = useParams();
  return <UpdateEmployee dep_id={dep_id} id={id} />;
};

export default UpdateEmployeeWrapper;
