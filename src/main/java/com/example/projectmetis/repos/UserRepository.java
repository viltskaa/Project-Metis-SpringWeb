package com.example.projectmetis.repos;

import com.example.projectmetis.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    User findUsersById(Long id);
    Optional<User> findByName(String name);
}
