import React, { Component } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { deleteEmployee } from "../../actions/EmployeeActions";

class Employee extends Component {
  onDeleteClick = (id) => {
    this.props.deleteEmployee(id);
  };

  render() {
    const { employee } = this.props;
    const dep_id = this.props.id;
    return (
      <React.Fragment>
        <div className="container">
          <div className="card card-body bg-light mb-3">
            <div className="row">
              <div className="col-lg-6 col-md-4 col-8">
                <h5>{employee.name}</h5>
                <h5>{employee.address}</h5>
                <h5>{employee.email}</h5>
                <h5>{employee.phone}</h5>
              </div>
              <div className="col-md-4 d-none d-lg-block">
                <ul className="list-group">
                  <Link to={`/employeeBoard/${dep_id}/${employee.id}`}>
                    <li className="list-group-item update">
                      <i className="fa fa-edit pr-1">Employee board</i>
                    </li>
                  </Link>
                  <Link to={`/updateEmployee/${dep_id}/${employee.id}`}>
                    <li className="list-group-item update">
                      <i className="fa fa-edit pr-1">Update employee</i>
                    </li>
                  </Link>
                  <Link to="">
                    <li
                      className="list-group-item delete"
                      onClick={this.onDeleteClick.bind(this, employee.id)}
                    >
                      <i className="fa fa-minus-circle pr-1">Delete employee</i>
                    </li>
                  </Link>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

Employee.propTypes = {
  deleteEmployee: PropTypes.func.isRequired,
};

export default connect(null, { deleteEmployee })(Employee);
