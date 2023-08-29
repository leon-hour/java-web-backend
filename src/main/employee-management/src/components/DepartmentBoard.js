import React, { Component } from "react";
import Employee from "./employees/Employee";
import { connect } from "react-redux";
import { getEmployeeList } from "../actions/EmployeeActions";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

class DepartmentBoard extends Component {
  componentDidMount() {
    this.props.getEmployeeList(this.props.id);
  }

  render() {
    const id = this.props.id;
    const employeeObject = this.props.employee.employees;
    return (
      <div className="container">
        <div className="container">
          <React.Fragment>
            <Link
              to={`/addEmployee/${id}`}
              className="btn btn-lg float-left btn-info"
            >
              Create employee
            </Link>
            <hr />
          </React.Fragment>
        </div>
        <h1>Employees</h1>
        {employeeObject.map((employee) => (
          <Employee id={this.props.id} key={employee.id} employee={employee} />
        ))}
      </div>
    );
  }
}

DepartmentBoard.propTypes = {
  getEmployeeList: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  employee: state.employeeReducerContent,
});

export default connect(mapStateToProps, { getEmployeeList })(DepartmentBoard);
