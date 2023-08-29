import React, { Component } from "react";
import Department from "./departments/Department";
import CreateButton from "./departments/CreateDepartmentButton";
import { getDepartments } from "../actions/DepartmentActions";
import { connect } from "react-redux";
import PropTypes from "prop-types";

class Departments extends Component {
  componentDidMount() {
    this.props.getDepartments();
  }

  render() {
    const departmentList = this.props.department.departments;
    return (
      <div className="container">
        <div className="container">
          <CreateButton />
        </div>
        <div className="container"></div>
        {departmentList.map((department) => (
          <Department key={department.id} department={department} />
        ))}
      </div>
    );
  }
}
Departments.propTypes = {
  getDepartments: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  department: state.departmentReducerContent,
});

export default connect(mapStateToProps, { getDepartments })(Departments);
