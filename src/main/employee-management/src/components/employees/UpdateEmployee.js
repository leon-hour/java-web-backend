import React, { Component } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { getEmployee, createEmployee } from "../../actions/EmployeeActions";

class UpdateEmployee extends Component {
  constructor() {
    super();

    this.state = {
      id: "",
      name: "",
      address: "",
      email: "",
      phone: "",
      departmentBacklog: "",
      errors: {},
    };
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  onSubmit(e) {
    e.preventDefault();
    const updateEmployee = {
      id: this.state.id,
      name: this.state.name,
      address: this.state.address,
      email: this.state.email,
      phone: this.state.phone,
    };
    this.props.createEmployee(
      updateEmployee,
      this.props.dep_id,
      this.props.emp_id
    );
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
    const { id, name, address, email, phone } = nextProps.employee;
    this.setState({ id, name, address, email, phone });
  }

  componentDidMount() {
    const id = this.props.id;
    const dep_id = this.props.dep_id;
    this.props.getEmployee(dep_id, id);
  }

  render() {
    const errors = this.props.errors;
    return (
      <div>
        <div className="employee">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">Update employee form</h5>
                <hr />
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg "
                    placeholder="id"
                    name="id"
                    disabled
                    value={this.state.id}
                    onChange={this.onChange}
                  />
                </div>
                <p></p>
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className="form-control form-control-lg "
                      placeholder="Employee Name"
                      //should be the same as in the backend
                      value={this.state.name}
                      onChange={this.onChange}
                      name="name"
                    />
                  </div>
                  <p>{errors.name}</p>
                  <div className="form-group">
                    <input
                      type="text"
                      className="form-control form-control-lg"
                      placeholder="Employee address"
                      name="address"
                      value={this.state.address}
                      onChange={this.onChange}
                    />
                    <p>{errors.address}</p>
                  </div>
                  <div className="form-group">
                    <input
                      type="text"
                      className="form-control form-control-lg"
                      placeholder={this.state.email}
                      name="email"
                      value={this.state.email}
                      onChange={this.onChange}
                    />
                  </div>
                  <p>{errors.email}</p>
                  <div className="form-group">
                    <input
                      type="text"
                      className="form-control form-control-lg"
                      placeholder="Phone number"
                      name="phone"
                      value={this.state.phone}
                      onChange={this.onChange}
                    />
                    <p>{errors.phone}</p>
                  </div>

                  <input
                    type="submit"
                    className="btn btn-primary btn-block mt-4"
                  />
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state) => ({
  employee: state.employeeReducerContent.employee,
  errors: state.errors,
});

UpdateEmployee.propTypes = {
  getEmployee: PropTypes.func.isRequired,
  createEmployee: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
};

export default connect(mapStateToProps, { getEmployee, createEmployee })(
  UpdateEmployee
);
