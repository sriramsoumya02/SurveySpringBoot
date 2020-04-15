package com.soumya.springbasic.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "users", collectionResourceRel = "users")
public interface UserRepositoryRest extends PagingAndSortingRepository<User, Long> {
    List<User> findByRole(@Param("role") String role);
}
