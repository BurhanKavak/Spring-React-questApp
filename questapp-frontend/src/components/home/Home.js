import Post from "../post/Post";
import PostForm from "../post/PostForm";
import React, { useEffect, useState } from "react";
import PostService from "../../service/PostService";
import Box from "@mui/material/Box";
import { CssBaseline } from "@mui/material";

function Home() {
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [postList, setPostList] = useState([]);

  const refreshPost = () => {
    PostService.getAllPost()
      .then((resp) => setPostList(resp.data))
      .catch((err) => setError(err));
    setIsLoaded(true);
  };

  useEffect(() => {
    refreshPost();
  }, [postList]);

  if (error) {
    return <div> Error!!!</div>;
  } else if (!isLoaded) {
    return <div> Loading...</div>;
  } else {
    return (
      <div>
        <CssBaseline />
        <div>
          <Box sx={{ bgcolor: "#E6E6FA", height: "100%" }}>
            <PostForm
              userName={"post.userName"}
              userId={1}
              refreshPost={refreshPost}
            />
            {postList.map((post) => (
              <Post
                postId={post.id}
                userName={post.userName}
                userId={post.userId}
                title={post.title}
                text={post.text}
                postLikes={post.postLikes}
              />
            ))}
          </Box>
        </div>
      </div>
    );
  }
}
export default Home;
