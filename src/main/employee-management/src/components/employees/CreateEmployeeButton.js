import React from "react";
import { Link } from "react-router-dom";

const CreateEmployeeButton = () => {
  const id = this.props.id;
  return (
    <React.Fragment>
      <Link
        to={`/addEmployee/${id}`}
        className="btn btn-lg float-left btn-info"
      >
        Create employee
      </Link>
      <hr />
    </React.Fragment>
  );
};
export default CreateEmployeeButton;
