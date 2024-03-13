package com.example.projectmetis.repos;

import com.example.projectmetis.models.Table;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends MongoRepository<Table,Long> {
    Table findTableById(Long id);
}
