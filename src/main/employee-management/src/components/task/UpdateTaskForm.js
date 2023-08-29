import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { createTask, getTask } from "../../actions/TaskActions";
import PropTypes from "prop-types";

class UpdateTaskForm extends Component {
  //create the contructor
  constructor() {
    super();
    //set the initial state

    this.state = {
      summary: "",
      acceptanceCriteria: "",
      status: "INPUT QUEUE",
      priority: 3,
      employee: "",
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

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  componentDidMount() {
    const dep_id = this.props.dep_id;
    const emp_id = this.props.emp_id;
    const id = this.props.id;
    this.props.getTask(dep_id, emp_id, id);
  }

  onSubmit(e) {
    e.preventDefault();
    const newTask = {
      summary: this.state.summary,
      id: this.state.id,
      status: this.state.status,
      acceptanceCriteria: this.state.acceptanceCriteria,
      priority: this.state.priority,
    };
    console.log(newTask);
    this.props.createTask(newTask, this.props.dep_id, this.props.emp_id);
  }
  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
    const { id, summary, status, acceptanceCriteria, priority } =
      nextProps.task;
    this.setState({ id, summary, status, acceptanceCriteria, priority });
  }

  render() {
    const dep_id = this.props.dep_id;
    const emp_id = this.props.emp_id;
    const { errors } = this.state;
    return (
      <div className="container">
        <div className="add-PBI">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <Link
                  to={`/employeeBoard/${dep_id}/${emp_id}`}
                  className="btn btn-light"
                >
                  Back to Employee Board
                </Link>
                <h4 className="display-4 text-center">Update Task</h4>
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className="form-control form-control-lg"
                      name="summary"
                      placeholder="Project Task summary"
                      value={this.state.summary}
                      onChange={this.onChange}
                    />
                  </div>
                  <p>{errors.summary}</p>
                  <div className="form-group">
                    <textarea
                      className="form-control form-control-lg"
                      placeholder="Acceptance Criteria"
                      name="acceptanceCriteria"
                      value={this.state.acceptanceCriteria}
                      onChange={this.onChange}
                    ></textarea>
                  </div>
                  <p>{errors.acceptanceCriteria}</p>
                  <div className="form-group">
                    <select
                      className="form-control form-control-lg"
                      name="priority"
                      value={this.state.priority}
                      onChange={this.onChange}
                    >
                      <option value={0}>Select Priority</option>
                      <option value={1}>High</option>
                      <option value={2}>Medium</option>
                      <option value={3}>Low</option>
                    </select>
                  </div>
                  <p></p>
                  <div className="form-group">
                    <div class="form-group">
                      <select
                        class="form-control form-control-lg"
                        name="status"
                        value={this.state.status}
                        onChange={this.onChange}
                      >
                        <option value="">Select Status</option>
                        <option value="INPUT QUEUE">INPUT QUEUE</option>
                        <option value="IN_PROGRESS">IN PROGRESS</option>
                        <option value="DONE">DONE</option>
                      </select>
                    </div>
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
  task: state.taskReducerContent.task,
  errors: state.errors,
});

UpdateTaskForm.protoTypes = {
  getTask: PropTypes.func.isRequired,
};

export default connect(mapStateToProps, { createTask, getTask })(
  UpdateTaskForm
);
