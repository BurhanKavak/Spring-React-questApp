import { CardContent, InputAdornment, OutlinedInput } from "@mui/material";

import "../../style/style.css";
import { purple } from "@mui/material/colors";
import { Link } from "react-router-dom";
import Avatar from "@mui/material/Avatar";
import React from "react";

function Comment(props) {
  const { text, userId, userName } = props;

  return (
    <CardContent className="container-size">
      <OutlinedInput
        disabled
        id="Outlined secondary"
        color="secondary"
        multiline
        inputProps={{ maxLength: 250 }}
        fullWidth
        value={text}
        startAdornment={
          <InputAdornment position="start">
            <Link
              className="navbar-header"
              to={{ pathname: "/users/" + userId }}
            >
              <Avatar sx={{ bgcolor: purple[500] }} aria-label="recipe">
                {userName.charAt(0).toUpperCase()}
              </Avatar>
            </Link>
          </InputAdornment>
        }
      ></OutlinedInput>
    </CardContent>
  );
}
export default Comment;
