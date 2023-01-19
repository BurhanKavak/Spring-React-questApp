import axios from "axios";

const base_url = "http://localhost:8080/likes";

class LikeService {
  async createOneLike(like) {
    return await axios.post(base_url, like);
  }

  async deleteOneLike(likeId) {
    const url = `${base_url}/${likeId}`;
    axios.delete(url);
  }
}

export default new LikeService();
