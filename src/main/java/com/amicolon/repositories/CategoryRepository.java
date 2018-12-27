package com.amicolon.repositories;

import com.amicolon.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>
{
	Optional<Category> findById(Long id);
}
