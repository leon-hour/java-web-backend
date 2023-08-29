import React, { Component } from "react";
import { Link } from "react-router-dom";
import Task from "./task/Task";
import { connect } from "react-redux";
import { getTasks } from "../actions/TaskActions";
import PropTypes from "prop-types";

class EmployeeBoard extends Component {
  componentDidMount() {
    this.props.getTasks(this.props.dep_id, this.props.emp_id);
  }

  render() {
    const dep_id = this.props.dep_id;
    const emp_id = this.props.emp_id;
    const tasks = this.props.task.tasks.map((task) => (
      <Task
        dep_id={this.props.dep_id}
        emp_id={this.props.emp_id}
        key={task.id}
        task={task}
      />
    ));
    let inputQueue = [];
    let inProgress = [];
    let done = [];

    for (const element of tasks) {
      if (element.props.task.status === "INPUT QUEUE") {
        inputQueue.push(element);
      } else if (element.props.task.status === "IN_PROGRESS") {
        inProgress.push(element);
      } else if (element.props.task.status === "DONE") {
        done.push(element);
      }
    }
    return (
      <div>
        <div className="container">
          <Link
            to={`/addTask/${dep_id}/${emp_id}`}
            className="btn btn-primary mb-3"
          >
            <i className="fas fa-plus-circle"> Create Employee Task</i>
          </Link>
          <br />
          <hr />
          <div className="container">
            <div className="row">
              <div className="col-md-4">
                <div className="card text-center mb-2">
                  <div className="card-header bg-secondary text-white">
                    <h3>Input Queue</h3>
                  </div>
                  <div>{inputQueue}</div>
                </div>
              </div>
              <div className="col-md-4">
                <div className="card text-center mb-2">
                  <div className="card-header bg-primary text-white">
                    <h3>In Progress</h3>
                  </div>
                  <div>{inProgress}</div>
                  <div></div>
                </div>
              </div>
              <div className="col-md-4">
                <div className="card text-center mb-2">
                  <div className="card-header bg-success text-white">
                    <h3>Done</h3>
                  </div>
                  <div>{done}</div>
                  <div></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
EmployeeBoard.propTypes = {
  task: PropTypes.object.isRequired,
  getTasks: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  task: state.taskReducerContent,
});

export default connect(mapStateToProps, { getTasks })(EmployeeBoard);
