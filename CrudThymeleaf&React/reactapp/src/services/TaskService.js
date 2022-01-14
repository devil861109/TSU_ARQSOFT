import axios from "axios";

const USER_REST_API_URL = "http://localhost:8080/todolist/tasks"

class TaskService {

    getAllTasks() {
        return axios.get(USER_REST_API_URL);
    }

    getTaskById(id) {
        return axios.get(USER_REST_API_URL + '/' + id);
    }

    createTask(task) {
        return axios.post(USER_REST_API_URL, task);
    }

    updateTask(id, task) {
        return axios.post(USER_REST_API_URL + '/' + id, task);
    }

    deleteAllTasks() {
        return axios.delete(USER_REST_API_URL);
    }

    deleteTaskById(id) {
        return axios.delete(USER_REST_API_URL + '/' + id);
    }
}

export default new TaskService()