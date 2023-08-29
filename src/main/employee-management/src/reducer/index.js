//to combine our reducers
import { combineReducers } from "redux";
import departmentReducer from "./DepartmentReducer";
import employeeReducer from "./EmployeeReducer";
import ErrorReducer from "./ErrorReducer";
import TaskReducer from "./TaskReducer";
import SecurityReducer from "./SecurityReducer";

export default combineReducers({
  errors: ErrorReducer,
  departmentReducerContent: departmentReducer,
  employeeReducerContent: employeeReducer,
  taskReducerContent: TaskReducer,
  security: SecurityReducer,
});
