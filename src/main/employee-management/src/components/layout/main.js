import React, { Component } from "react";

class PublicView extends Component {
  render() {
    return (
      <div>
        <h1>Main content here...</h1>
        <ul>
          {" "}
          <li>Restaurants</li>
          <li>Reservations</li>
          <li>Order</li>
        </ul>
      </div>
    );
  }
}

export default PublicView;
