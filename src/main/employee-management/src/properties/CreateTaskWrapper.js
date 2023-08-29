import React from "react";
import { useParams } from "react-router-dom";
import AddTaskForm from "../components/task/AddTaskForm";

const CreateTaskWrapper = () => {
  const { dep_id } = useParams();
  const { emp_id } = useParams();
  return <AddTaskForm dep_id={dep_id} emp_id={emp_id} />;
};

export default CreateTaskWrapper;
