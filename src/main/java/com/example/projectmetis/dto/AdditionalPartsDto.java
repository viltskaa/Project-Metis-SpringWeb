package com.example.projectmetis.dto;


import com.example.projectmetis.models.Table;
import com.example.projectmetis.models.User;
import lombok.Data;

@Data
public class AdditionalPartsDto {
    private Long id;
    private Long timeAssembly;
    private String article;
    private User user;
    private Table table;
}
