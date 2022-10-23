import React, { useState } from "react";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardContent from "@mui/material/CardContent";
import Avatar from "@mui/material/Avatar";
import Typography from "@mui/material/Typography";
import { purple } from "@mui/material/colors";
import { Link } from "react-router-dom";
import "../../style/style.css";
import { Button, OutlinedInput } from "@mui/material";
import TextField from "@mui/material/TextField";
import PostService from "../../service/PostService";
import Snackbar from "@mui/material/Snackbar";
import MuiAlert from "@mui/material/Alert";

const Alert = React.forwardRef(function Alert(props, ref) {
  return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

function PostForm(props) {
  const { userId, userName, refreshPost } = props;
  const [text, setText] = useState("");
  const [title, setTitle] = useState("");
  const [isSent, setIsSent] = useState(false);

  const handleAddPost = () => {
    const posts = {
      title,
      text,
      userId,
    };
    PostService.createOnePost(posts)
      .then((resp) => resp.data)
      .then((resp) => {
        console.log(resp);
        setIsSent(true);
        setTitle("");
        setText("");
        refreshPost();
      });
  };

  const handleText = (e) => {
    console.log(e.target.value);
    setText(e.target.value);
    setIsSent(false);
  };

  const handleTitle = (e) => {
    console.log(e.target.value);
    setTitle(e.target.value);
    setIsSent(false);
  };
  const handleClose = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }

    setIsSent(false);
  };

  return (
    <div className="container-size">
      <Snackbar
        anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
        open={isSent}
        autoHideDuration={1200}
        onClose={handleClose}
      >
        <Alert onClose={handleClose} severity="success" sx={{ width: "100%" }}>
          <b>Your post is sent!</b>
        </Alert>
      </Snackbar>
      <Card>
        <CardHeader
          avatar={
            <Link
              className="navbar-header"
              to={{ pathname: "/users/" + userId }}
            >
              <Avatar sx={{ bgcolor: purple[500] }} aria-label="recipe">
                {userName.charAt(0).toUpperCase()}
              </Avatar>
            </Link>
          }
          title={
            <div>
              <TextField
                id="Outlined secondary"
                color="secondary"
                label="Title"
                variant="outlined"
                multiline
                placeholder="Title"
                value={title}
                inputProps={{ maxLength: 25 }}
                onChange={(e) => handleTitle(e)}
                fullWidth
              />
            </div>
          }
        />

        <CardContent>
          <Typography variant="body2" color="text.secondary">
            <div>
              <OutlinedInput
                id="Outlined secondary"
                color="secondary"
                multiline
                placeholder="Text"
                inputProps={{ maxLength: 250 }}
                fullWidth
                value={text}
                onChange={(e) => handleText(e)}
                endAdornment={
                  <Button
                    onClick={() => handleAddPost()}
                    variant="contained"
                    color="secondary"
                  >
                    Post
                  </Button>
                }
              />
            </div>
          </Typography>
        </CardContent>
      </Card>
    </div>
  );
}
export default PostForm;
