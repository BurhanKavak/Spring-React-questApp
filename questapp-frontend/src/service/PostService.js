import axios from "axios";

const base_url = "http://localhost:8080/posts";

class PostService {
  async getAllPost() {
    return await axios.get(base_url);
  }
  async createOnePost(post) {
    return await axios.post(base_url, post);
  }
}
export default new PostService();
