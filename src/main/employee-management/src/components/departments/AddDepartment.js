import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createDepartment } from "../../actions/DepartmentActions";

class AddDepartment extends Component {
  constructor() {
    super();

    this.state = {
      name: "",
      errors: {},
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  static getDerivedStateFromProps(nextProps, prevState) {
    if (nextProps.errors !== prevState.errors) {
      return { errors: nextProps.errors };
    }
    return { errors: nextProps.errors };
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }
  onSubmit(e) {
    e.preventDefault();
    const newDepartment = {
      name: this.state.name,
    };
    this.props.createDepartment(newDepartment);
  }

  render() {
    const { errors } = this.state;
    return (
      //--------Html----------
      <div>
        <div className="register">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">
                  Create Department form
                </h5>
                <hr />
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className="form-control form-control-lg "
                      placeholder="Department name"
                      name="name"
                      value={this.state.name}
                      //bind the function
                      onChange={this.onChange}
                    />
                    <p>{errors.name}</p>
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
      //------------Html--------------
    );
  }
}

AddDepartment.propTypes = {
  //seting constrains
  createDepartment: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  errors: state.errors,
});

export default connect(mapStateToProps, { createDepartment })(AddDepartment);
