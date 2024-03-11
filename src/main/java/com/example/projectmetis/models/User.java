package com.example.projectmetis.models;

import com.nimbusds.jose.util.Pair;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {
    @Id
    private Long id;
    private String name;
    private List<Pair<Integer, Integer>> work;
    @DBRef
    private List<AdditionalParts> additionalParts;
    @DBRef
    private List<Table> tables;
    @DBRef
    private List<TableTop> tableTops;
}
