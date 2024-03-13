package com.example.projectmetis.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class AdditionalParts {
    @Id
    private Long id;
    private Long timeAssembly;
    private String article;
    private User user;
    private Table table;
}
