import axios from "axios";

const base_url = "http://localhost:8080/users";

class UserService {
  getUsers() {
    return axios.get(base_url);
  }
}

export default new UserService();
