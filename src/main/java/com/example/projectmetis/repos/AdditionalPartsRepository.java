package com.example.projectmetis.repos;

import com.example.projectmetis.models.AdditionalParts;
import com.example.projectmetis.models.Table;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdditionalPartsRepository extends MongoRepository<AdditionalParts, Long> {
    AdditionalParts findAdditionalPartsById(Long id);
    AdditionalParts findByArticleAndTable(String article, Table table);
}
