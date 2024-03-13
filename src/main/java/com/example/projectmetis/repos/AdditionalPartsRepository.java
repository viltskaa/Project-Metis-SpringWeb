package com.example.projectmetis.repos;

import com.example.projectmetis.models.AdditionalParts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalPartsRepository extends MongoRepository<AdditionalParts, Long> {
    AdditionalParts findAdditionalPartsById(Long id);
}
