package com.example.projectmetis.dto;


import com.example.projectmetis.models.AdditionalParts;
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

    public AdditionalPartsDto(){};

    public AdditionalPartsDto(AdditionalParts additionalParts){
        id = additionalParts.getId();
        timeAssembly = additionalParts.getTimeAssembly();
        article = additionalParts.getArticle();
        user = additionalParts.getUser();
        table = additionalParts.getTable();
    }
}
