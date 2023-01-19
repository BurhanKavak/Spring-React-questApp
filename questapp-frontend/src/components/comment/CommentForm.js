import { CardContent, InputAdornment, OutlinedInput } from "@mui/material";

import "../../style/style.css";
import { purple } from "@mui/material/colors";
import { Link } from "react-router-dom";
import Avatar from "@mui/material/Avatar";
import Typography from "@mui/material/Typography";
import React, { useState } from "react";
import Button from "@mui/material/Button";
import CommentService from "../../service/CommentService";

function CommentForm(props) {
  const { postId, userId, userName, refreshComment } = props;
  const [text, setText] = useState("");

  const handleSubmit = () => {
    const comments = {
      userId,
      postId,
      text,
    };
    CommentService.createOneComment(comments)
      .then((resp) => resp.data)
      .then((resp) => {
        console.log(resp);
        setText("");
        refreshComment();
      });
  };

  const handleText = (e) => {
    console.log(e.target.value);
    setText(e.target.value);
  };

  return (
    <CardContent className="container-size">
      <Typography variant="body2" color="text.secondary">
        <div>
          <OutlinedInput
            id="Outlined secondary"
            color="secondary"
            multiline
            inputProps={{ maxLength: 250 }}
            fullWidth
            placeholder="Comment"
            value={text}
            onChange={(e) => handleText(e)}
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
            endAdornment={
              <Button
                onClick={() => handleSubmit()}
                color="secondary"
                variant="contained"
              >
                Comment
              </Button>
            }
          />
        </div>
      </Typography>
    </CardContent>
  );
}
export default CommentForm;
