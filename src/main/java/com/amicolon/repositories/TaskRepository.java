package com.amicolon.repositories;

import com.amicolon.domain.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long>
{
}
