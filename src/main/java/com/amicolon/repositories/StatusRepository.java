package com.amicolon.repositories;

import com.amicolon.domain.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long>
{
	Set<Status> findAll();
}
