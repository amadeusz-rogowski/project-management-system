package com.amicolon.repositories;

import com.amicolon.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long>
{
	Optional<Task> findById(Long id);
}
