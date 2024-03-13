package com.example.projectmetis.dto;

import com.example.projectmetis.models.User;
import lombok.Data;

@Data
public class TableTopDto {
    private Long id;
    private double width;
    private double height;
    private double perimeter;
    private double depth;
    private String colorMain;
    private String colorEdge;
    private String material;
    private String article;
    private Long timeAssembly;
    private User user;
}
