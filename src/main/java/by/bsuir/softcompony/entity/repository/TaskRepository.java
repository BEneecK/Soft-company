package by.bsuir.softcompony.entity.repository;

import by.bsuir.softcompony.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
