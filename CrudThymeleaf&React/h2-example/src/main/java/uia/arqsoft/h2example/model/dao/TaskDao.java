package uia.arqsoft.h2example.model.dao;

import org.springframework.data.repository.CrudRepository;
import uia.arqsoft.h2example.model.entity.Task;

public interface TaskDao extends CrudRepository<Task, Long> {
    //CRUD
}
