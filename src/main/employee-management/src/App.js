import React, { Component } from "react";
import "./App.css";
import Departments from "./components/Departments";
import Header from "./components/layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddDepartment from "./components/departments/AddDepartment";
import { Provider } from "react-redux";
import store from "./store";
import UpdateDepartmentWrapper from "./properties/UpdateDepartmentWrapper";
import UpdateEmployeeWrapper from "./properties/UpdateEmployeeWrapper";
import EmployeeBoardWrapper from "./properties/EmployeeBoardWraper";
import CreateTaskWrapper from "./properties/CreateTaskWrapper";
import DepartmentBoardWrapper from "./properties/DepartmentBoardWrapper";
import Employees from "./components/Employees";
import CreateEmployeeWrapper from "./properties/CreateEmployeeWraper";
import Tasks from "./components/Tasks";
import UpdateTaskWrapper from "./properties/UpdateTaskWrapper";
import Login from "./components/Login";
import Register from "./components/Register";
import jwt_decode from "jwt-decode";
import setJWTToken from "./securityUtils/setJWTToken";
import { SET_CURRENT_USER } from "./actions/types";
import { logout } from "./actions/SecurityActions";
import PrivateRoutes from "./securityUtils/routeSecure";
import PublicView from "./components/layout/main";

const jwtToken = localStorage.jwtToken;

if (jwtToken) {
  setJWTToken(jwtToken);
  const decoded_jwtToken = jwt_decode(jwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decoded_jwtToken,
  });

  const currentTime = Date.now() / 1000;
  if (decoded_jwtToken.exp < currentTime) {
    store.dispatch(logout());
    window.location.href = "/";
  }
}

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <Header />
          <Routes>
            <Route exact path="/main" element={<PublicView />}></Route>
            <Route exact path="/register" element={<Register />}></Route>
            <Route exact path="/login" element={<Login />}></Route>
            <Route element={<PrivateRoutes />}>
              <Route element={<Departments />} path="/department" exact />
              <Route element={<Employees />} path="/employee" />
              <Route exact path="/" element={<Register />}></Route>
              <Route element={<Departments />} path="/department" exact></Route>
              <Route exact path="/addDepartment" element={<AddDepartment />} />
              <Route exact path="/employee" element={<Employees />} />
              <Route
                exact
                path="/addEmployee/:id"
                element={<CreateEmployeeWrapper />}
              />
              <Route
                exact
                path="/addTask/:dep_id/:emp_id"
                element={<CreateTaskWrapper />}
              />
              <Route
                exact
                path="/employees/:id"
                element={<DepartmentBoardWrapper />}
              />
              <Route
                exact
                path="/employeeBoard/:dep_id/:emp_id"
                element={<EmployeeBoardWrapper />}
              />
              <Route exact path="/taskBoard" element={<Tasks />} />
              <Route
                exact
                path="/updateEmployee/:dep_id/:id"
                element={<UpdateEmployeeWrapper />}
              />
              <Route
                exact
                path="/updateTask/:dep_id/:emp_id/:id"
                element={<UpdateTaskWrapper />}
              />
              <Route
                exact
                path="/updateDepartment/:id"
                element={<UpdateDepartmentWrapper />}
              />
            </Route>
          </Routes>
        </Router>
      </Provider>
    );
  }
}
export default App;
