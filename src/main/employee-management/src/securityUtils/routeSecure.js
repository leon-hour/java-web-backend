import React from "react";
import { Outlet, Navigate } from "react-router-dom";
import { connect } from "react-redux";
import PropTypes from "prop-types";

const PrivateRotes = ({ path, Component, security }) => {
  const authenticated = security.validToken;

  // Add your authentication logic here, such as checking if the user is logged in

  if (authenticated) {
    return <Outlet path={path} element={Component} />;
  } else {
    return <Navigate to="/login" />;
  }
};

PrivateRotes.propTypes = {
  security: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  security: state.security,
});

export default connect(mapStateToProps)(PrivateRotes);
