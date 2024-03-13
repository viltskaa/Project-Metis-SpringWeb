package com.example.projectmetis.repos;

import com.example.projectmetis.models.TableTop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableTopRepository extends MongoRepository<TableTop,Long> {
    TableTop findTableTopById(Long id);
}
