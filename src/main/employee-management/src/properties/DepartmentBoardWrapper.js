import React from "react";
import { useParams } from "react-router-dom";
import DepartmentBoard from "../components/DepartmentBoard";

const DepartmentBoardWrapper = () => {
  const { id } = useParams();
  return <DepartmentBoard id={id} />;
};

export default DepartmentBoardWrapper;
