import {
  DELETE_DEPARTMENT,
  GET_DEPARTMENT,
  GET_DEPARTMENTS,
} from "../actions/types";

const initialState = {
  departments: [],
  department: {},
};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_DEPARTMENTS:
      return {
        ...state,
        // here we will recive the data from out fuction in departmentAction.
        // then those data will be pased to the rootReducer and then from the rootReducer to our departmentComponent
        departments: action.payload,
      };

    case GET_DEPARTMENT:
      return {
        // to copy the current state and then update it
        ...state,
        department: action.payload,
      };
    case DELETE_DEPARTMENT:
      return {
        ...state,
        departments: state.departments.filter(
          (department) => department.id !== action.payload
        ),
      };

    default:
      return state;
  }
}
