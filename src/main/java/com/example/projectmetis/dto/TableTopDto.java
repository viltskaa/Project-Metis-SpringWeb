package com.example.projectmetis.dto;

import com.example.projectmetis.models.TableTop;
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

    public TableTopDto(){}

    public TableTopDto(TableTop tableTop){
        id=tableTop.getId();
        article=tableTop.getArticle();
        timeAssembly=tableTop.getTimeAssembly();
        width=tableTop.getWidth();
        height=tableTop.getHeight();
        depth=tableTop.getDepth();
        colorEdge=tableTop.getColorEdge();
        colorMain=tableTop.getColorMain();
        material=tableTop.getMaterial();
        user=tableTop.getUser();
        perimeter=tableTop.getPerimeter();
    }
}
