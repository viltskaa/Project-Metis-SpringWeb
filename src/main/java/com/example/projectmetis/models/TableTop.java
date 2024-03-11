package com.example.projectmetis.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Setter
@Getter
public class TableTop {
    @Id
    private long id;
    private double width;
    private double height;
    private double perimeter;
    private double depth;
    private String colorMain;
    private String colorEdge;
    private String material;
    @Indexed(unique = true)
    private String article;
    private long timeAssembly;
    private User user;

}
