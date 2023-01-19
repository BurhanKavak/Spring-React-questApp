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
import LikeService from "../../service/LikeService";

function Post(props) {
  const { postId, title, text, userId, userName, postLikes } = props;
  const [expanded, setExpanded] = useState(false);
  const [liked, setLiked] = useState(false);
  const [error, setError] = useState(null);
  const [commentList, setCommentList] = useState([]);
  const [isLoaded, setIsLoaded] = useState(false);
  const isInitialMount = useRef(true);
  const [likeCount, setLikeCount] = useState(postLikes.length);
  const [likeId, setLikeId] = useState(null);

  const handleExpandClick = () => {
    setExpanded(!expanded);
    refreshComment();
    console.log(commentList);
  };

  const handleLike = () => {
    setLiked(!liked);
    if (!liked) {
      saveLike();
      setLikeCount(likeCount + 1);
    } else {
      deleteLike();
      setLikeCount(likeCount - 1);
    }
  };

  const refreshComment = () => {
    CommentService.getAllComment(postId)
      .then((resp) => setCommentList(resp.data))
      .catch((err) => setError(err));
    setIsLoaded(true);
  };

  const saveLike = () => {
    const likes = {
      postId,
      userId,
    };
    LikeService.createOneLike(likes)
      .then((resp) => resp.data)
      .then((resp) => {
        console.log(resp);
      });
  };

  const deleteLike = () => {
    LikeService.deleteOneLike(likeId)
      .then((resp) => {
        console.log(resp);
      })
      .catch((err) => console.log(err));
  };

  const checkLikes = () => {
    var likeControl = postLikes.find((like) => like.userId === userId);
    if (likeControl != null) {
      setLikeId(likeControl.id);
      setLiked(true);
    }
  };

  useEffect(() => {
    if (isInitialMount.current) {
      // isInitialMount = false;
    } else {
      refreshComment();
    }
  }, [commentList]);

  useEffect(() => {
    checkLikes();
  }, []);

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
          {likeCount}

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
              postId={postId}
              refreshComment={refreshComment}
            ></CommentForm>
          </Container>
        </Collapse>
      </Card>
    </div>
  );
}
export default Post;
