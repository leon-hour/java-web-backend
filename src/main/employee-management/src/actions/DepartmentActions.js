import axios from "axios";
import {
  GET_ERRORS,
  GET_DEPARTMENTS,
  GET_DEPARTMENT,
  DELETE_DEPARTMENT,
} from "./types";

//arrow function
export const createDepartment = (department, history) => async (dispatch) => {
  try {
    await axios.post("http://localhost:8095/api/departments", department);
    window.location.href = `/department`;
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

export const getDepartments = () => async (dispatch) => {
  const res = await axios.get("http://localhost:8095/api/departments/all");
  dispatch({
    type: GET_DEPARTMENTS,
    //data that we get from our database
    //this is what we are going to pass to our reducer
    payload: res.data,
  });
};

export const getDepartment = (id) => async (dispatch) => {
  try {
    const res = await axios.get(
      `http://localhost:8095/api/departments/id/${id}`
    );
    dispatch({
      type: GET_DEPARTMENT,
      //data that we get from put database
      //this is what we are going to pass to our reducer
      payload: res.data,
    });
  } catch (error) {
    window.location.href = `/department`;
  }
};

export const deleteDepartment = (id) => async (dispatch) => {
  if (window.confirm("Are you sure you want to delete this department.")) {
    await axios.delete(`http://localhost:8095/api/departments/delete/${id}`);
    dispatch({
      type: DELETE_DEPARTMENT,
      payload: id,
    });
  }
};
