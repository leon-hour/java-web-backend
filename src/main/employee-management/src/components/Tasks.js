import React, { Component } from "react";
import Task from "./task/Task";
import { connect } from "react-redux";
import { getAllTasks } from "../actions/TaskActions";
import PropTypes from "prop-types";

class Tasks extends Component {
  componentDidMount() {
    this.props.getAllTasks();
  }

  render() {
    const tasks = this.props.task.tasks.map((task) => (
      <Task key={task.id} task={task} />
    ));
    let inputQueue = [];
    let inProgress = [];
    let done = [];

    for (const element of tasks) {
      if (element.props.task.status === "INPUT QUEUE") {
        console.log(element);
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
Tasks.propTypes = {
  getAllTasks: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  task: state.taskReducerContent,
});

export default connect(mapStateToProps, { getAllTasks })(Tasks);
