import React, { Component } from "react";
import { Link } from "react-router-dom";
import { deleteTask } from "../../actions/TaskActions";
import { connect } from "react-redux";
import PropTypes from "prop-types";

class Task extends Component {
  onDeleteClick = (id) => {
    this.props.deleteTask(id);
  };
  render() {
    const dep_id = this.props.dep_id;
    const emp_id = this.props.emp_id;
    const { task } = this.props;
    let priorityClassName;
    let priorityString;
    if (task.priority === 1) {
      priorityClassName = "bg-danger text-light";
      priorityString = "HIGH";
    }
    if (task.priority === 2) {
      priorityClassName = "bg-warning text-dark";
      priorityString = "MEDIUM";
    }
    if (task.priority === 3) {
      priorityClassName = "bg-info text-light";
      priorityString = "LOW";
    }

    return (
      <div className="card mb-1 bg-light">
        <div className={`card-header text-primary ${priorityClassName}`}>
          <h3>
            ID: {task.id} -- Priority: {priorityString}
          </h3>
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">{task.summary}</h5>
          <p className="card-text text-truncate ">{task.acceptanceCriteria}</p>
          <Link
            to={`/updateTask/${dep_id}/${emp_id}/${task.id}`}
            className="btn btn-primary"
          >
            View / Update
          </Link>

          <button
            className="btn btn-danger ml-4"
            onClick={this.onDeleteClick.bind(this, task.id)}
          >
            Delete
          </button>
        </div>
      </div>
    );
  }
}

Task.propTypes = {
  deleteTask: PropTypes.func.isRequired,
};

export default connect(null, { deleteTask })(Task);
