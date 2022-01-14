package uia.arqsoft.h2example.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uia.arqsoft.h2example.model.dao.TaskDao;
import uia.arqsoft.h2example.model.entity.Task;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    //@Autowired
    private TaskDao taskDao;

    /*@Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }*/

    @Autowired
    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public List<Task> getTask() {
        return (List<Task>) taskDao.findAll();
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskDao.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        taskDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        taskDao.deleteAll();
    }

    @Override
    public Task save(Task task) {
        taskDao.save(task);
        return task;
    }
}
