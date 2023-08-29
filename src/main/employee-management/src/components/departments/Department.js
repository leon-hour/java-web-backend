import React, { Component } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { deleteDepartment } from "../../actions/DepartmentActions";
import { connect } from "react-redux";

class Department extends Component {
  onDeleteClick = (id) => {
    this.props.deleteDepartment(id);
  };

  render() {
    const { department } = this.props;
    return (
      <React.Fragment>
        <div className="container">
          <div className="card card-body bg-light ">
            <div className="row">
              <div className="col-lg-6 col-md-4 col-8">
                <h3>{department.id}</h3>
                <h3>{department.name}</h3>
              </div>
              <div className="col-md-4 d-none d-lg-block">
                <ul className="list-group">
                  {
                    //create department Board element that will send us to a page that will display the employee list of that department
                  }
                  <Link to={`/employees/${department.id}`}>
                    <li className="list-group-item">
                      <i className="fa fa-edit pr-1">Department board</i>
                    </li>
                  </Link>
                  <Link to={`/updateDepartment/${department.id}`}>
                    <li className="list-group-item update">
                      <i className="fa fa-edit pr-1">Update department</i>
                    </li>
                  </Link>
                  <Link>
                    <li
                      className="list-group-item delete"
                      onClick={this.onDeleteClick.bind(this, department.id)}
                    >
                      <i className="fa fa-minus-circle pr-1">
                        Delete department
                      </i>
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

Department.propTypes = {
  deleteDepartment: PropTypes.func.isRequired,
};

export default connect(null, { deleteDepartment })(Department);
