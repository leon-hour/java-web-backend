import React, { Component } from "react";
import Employee from "./employees/Employee";
import { connect } from "react-redux";
import { getEmployees } from "../actions/EmployeeActions";
import PropTypes from "prop-types";

class Employees extends Component {
  componentDidMount() {
    this.props.getEmployees();
  }

  render() {
    //jsx a flavor of html that you can write inside your JavaScript code
    const employeeObject = this.props.employee.employees;
    return (
      <div className="container">
        <h1>Employees</h1>
        {employeeObject.map((employee) => (
          <Employee key={employee.id} employee={employee} />
        ))}
      </div>
    );
  }
}

Employees.propTypes = {
  getEmployees: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  employee: state.employeeReducerContent,
});

export default connect(mapStateToProps, { getEmployees })(Employees);
