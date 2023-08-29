//first you import the action
import { GET_ERRORS } from "../actions/types";
//then create the initial state
const initialState = {};
//export the function that takes tha state and the action
export default function (state = initialState, action) {
  switch (action.type) {
    case GET_ERRORS:
      return action.payload;

    default:
      return state;
  }
}
