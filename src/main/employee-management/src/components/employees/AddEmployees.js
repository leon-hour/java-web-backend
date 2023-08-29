import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createEmployee } from "../../actions/EmployeeActions";
import classnames from "classnames";

class AddEmployee extends Component {
  //create the contructor
  constructor() {
    super();
    //set the initial state

    this.state = {
      name: "",
      address: "",
      email: "",
      phone: "",
      errors: {},
    };
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentDidUpdate(prevProps) {
    if (this.props.errors !== prevProps.errors) {
      this.setState({ errors: this.props.errors });
    }
  }

  // set the value attribute on each input field to what's on the state for the specific input field
  // if we run the server we will see that we can not type any input on out text fields
  // so we will create a function called onChange

  //method1 more complicated
  // onChange(e) {
  //change the state then bind the function
  //this alows us to write on the input text fields
  // this.setState({ name: e.target.value });
  // }

  //method2
  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    // create a new employee object
    e.preventDefault();
    const newEmployee = {
      name: this.state.name,
      address: this.state.address,
      email: this.state.email,
      phone: this.state.phone,
    };
    this.props.createEmployee(newEmployee, this.props.id);
  }
  render() {
    const { errors } = this.state;
    return (
      <div>
        <div className="employee">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">Create employee form</h5>
                <hr />
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.name,
                      })}
                      placeholder="Employee Name"
                      //should be the same as in the backend
                      name="name"
                      //set the value
                      value={this.state.name}
                      //bind the function
                      onChange={this.onChange}
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
                      placeholder="Employee email"
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
  errors: state.errors,
});

AddEmployee.protoTypes = {
  createEmployee: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
};

export default connect(mapStateToProps, { createEmployee })(AddEmployee);
