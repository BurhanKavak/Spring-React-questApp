import React, { useEffect, useRef, useState } from "react";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import Collapse from "@mui/material/Collapse";
import Avatar from "@mui/material/Avatar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import { purple } from "@mui/material/colors";
import FavoriteIcon from "@mui/icons-material/Favorite";
import { Link } from "react-router-dom";
import CommentIcon from "@mui/icons-material/Comment";
import CommentService from "../../service/CommentService";
import "../../style/style.css";
import Comment from "../comment/Comment";
import { Container } from "@mui/system";
import CommentForm from "../comment/CommentForm";

function Post(props) {
  const { postId, title, text, userId, userName } = props;
  const [expanded, setExpanded] = useState(false);
  const [liked, setLiked] = useState(false);
  const [error, setError] = useState(null);
  const [commentList, setCommentList] = useState([]);
  const [isLoaded, setIsLoaded] = useState(false);
  const isInitialMount = useRef(true);

  const handleExpandClick = () => {
    setExpanded(!expanded);
    refreshComment();
    console.log(commentList);
  };

  const handleLike = () => {
    setLiked(!liked);
  };

  const refreshComment = () => {
    CommentService.getAllComment(postId)
      .then((resp) => setCommentList(resp.data))
      .catch((err) => setError(err));
    setIsLoaded(true);
  };

  useEffect(() => {
    if (isInitialMount.current) {
      // isInitialMount = false;
    } else {
      refreshComment();
    }
  }, [commentList]);

  return (
    <div className="container-size">
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
          title={title}
        />

        <CardContent>
          <Typography variant="body2" color="text.secondary">
            {text}
          </Typography>
        </CardContent>
        <CardActions disableSpacing>
          <IconButton onClick={handleLike} aria-label="add to favorites">
            <FavoriteIcon style={liked ? { color: "purple" } : null} />
          </IconButton>

          <div
            expand={expanded}
            onClick={handleExpandClick}
            aria-expanded={expanded}
            aria-label="show more"
          >
            <CommentIcon sx={{ marginLeft: "1100%" }} fontSize="large" />
          </div>
        </CardActions>
        <Collapse in={expanded} timeout="auto" unmountOnExit>
          <Container fixed>
            {error
              ? "Error"
              : isLoaded
              ? commentList.map((comment) => (
                  <Comment
                    userId={1}
                    userName={"USER"}
                    text={comment.text}
                  ></Comment>
                ))
              : "Loading"}
            <CommentForm
              userId={1}
              userName={"USER"}
              text={"Text"}
            ></CommentForm>
          </Container>
        </Collapse>
      </Card>
    </div>
  );
}
export default Post;
