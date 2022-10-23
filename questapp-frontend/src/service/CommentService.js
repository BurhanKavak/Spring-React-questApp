import axios from "axios";

const base_url = "http://localhost:8080/comments";

class CommentService {
  async getAllComment(postId) {
    return await axios.get(`${base_url}?postId=${postId}`);
  }
  async createOneComment(comment) {
    return await axios.post(base_url, comment);
  }
}

export default new CommentService();
