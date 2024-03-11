package com.example.projectmetis.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AdditionalParts {
    @Id
    private Long id;
    private Long timeAssembly;
    private String article;
    private User user;
}
