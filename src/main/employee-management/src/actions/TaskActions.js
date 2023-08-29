import axios from "axios";
import { GET_ERRORS, DELETE_TASK, GET_TASK, GET_TASKS } from "./types";

export const createTask = (task, dep_id, emp_id) => async (dispatch) => {
  try {
    await axios.post(
      `http://localhost:8095/api/tasks/${dep_id}/${emp_id}`,
      task
    );
    window.location.href = `/employeeBoard/${dep_id}/${emp_id}`;
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data,
    });
  }
};

export const getTasks = (dep_id, emp_id) => async (dispatch) => {
  const res = await axios.get(
    `http://localhost:8095/api/tasks/${dep_id}/${emp_id}`
  );
  dispatch({
    type: GET_TASKS,
    payload: res.data,
  });
};

export const getAllTasks = () => async (dispatch) => {
  const res = await axios.get("http://localhost:8095/api/tasks/all");
  dispatch({
    type: GET_TASKS,
    payload: res.data,
  });
};

export const getTask = (dep_id, emp_id, id) => async (dispatch) => {
  try {
    const res = await axios.get(
      `http://localhost:8095/api/tasks/${dep_id}/${emp_id}/${id}`
    );
    dispatch({
      type: GET_TASK,
      payload: res.data,
    });
  } catch (error) {
    console.log(error);
    window.location.href = "/taskBoard";
  }
};

export const deleteTask = (id) => async (dispatch) => {
  if (window.confirm("Are you sure you want to delete this Task?")) {
    await axios.delete(`http://localhost:8095/api/tasks/delete/${id}`);
    dispatch({
      type: DELETE_TASK,
      payload: id,
    });
  }
};
