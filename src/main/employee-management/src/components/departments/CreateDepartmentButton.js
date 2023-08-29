import React from "react";
import { Link } from "react-router-dom";
//functional component
// arrow function
const CreateButton = () => {
  return (
    //to avoid creating so many divs we can use React.Fragment to wrap the eleemnts in return
    // invisible for HTML
    <React.Fragment>
      <Link to="/addDepartment" className="btn btn-lg float-left btn-info">
        Create a department
      </Link>
      <hr />
    </React.Fragment>
  );
};
export default CreateButton;
