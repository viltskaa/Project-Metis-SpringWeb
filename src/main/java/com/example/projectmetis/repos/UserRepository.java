package com.example.projectmetis.repos;

import com.example.projectmetis.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    User findUsersById(Long id);
}
