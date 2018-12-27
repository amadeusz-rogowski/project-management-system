package com.amicolon.repositories;

import com.amicolon.domain.Priority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PriorityRepository extends CrudRepository<Priority, Long>
{
	Set<Priority> findAll();
}
