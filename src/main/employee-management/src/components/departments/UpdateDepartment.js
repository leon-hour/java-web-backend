import React, { Component } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import {
  getDepartment,
  createDepartment,
} from "../../actions/DepartmentActions";

class UpdateDepartment extends Component {
  constructor() {
    super();

    this.state = {
      id: "",
      name: "",
      errors: {},
    };
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
    const { id, name } = nextProps.department;
    this.setState({ id, name });
  }

  componentDidMount() {
    const id = this.props.id;
    this.props.getDepartment(id);
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();
    const updateProject = {
      id: this.state.id,
      name: this.state.name,
    };
    this.props.createDepartment(updateProject);
  }

  render() {
    const errors = this.props.errors;
    return (
      <div>
        <div className="register">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">
                  Update Department form
                </h5>
                <hr />
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className="form-control form-control-lg "
                      placeholder="Department id "
                      name="id"
                      value={this.state.id}
                      onChange={this.onChange}
                      disabled
                    />
                  </div>
                  <div className="form-group">
                    <input
                      type="text"
                      className="form-control form-control-lg "
                      placeholder="Department name"
                      name="name"
                      value={this.state.name}
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
    );
  }
}

UpdateDepartment.propTypes = {
  getDepartment: PropTypes.func.isRequired,
  createDepartment: PropTypes.func.isRequired,
  department: PropTypes.object.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  department: state.departmentReducerContent.department,
  errors: state.errors,
});

export default connect(mapStateToProps, { getDepartment, createDepartment })(
  UpdateDepartment
);
