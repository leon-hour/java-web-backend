import React from "react";
import { useParams } from "react-router-dom";
import UpdateTaskForm from "../components/task/UpdateTaskForm";

const UpdateTaskWrapper = () => {
  const { dep_id } = useParams();
  const { emp_id } = useParams();
  const { id } = useParams();
  return <UpdateTaskForm dep_id={dep_id} emp_id={emp_id} id={id} />;
};

export default UpdateTaskWrapper;
