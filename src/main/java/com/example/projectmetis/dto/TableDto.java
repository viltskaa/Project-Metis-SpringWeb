package com.example.projectmetis.dto;

import com.example.projectmetis.models.Table;
import com.example.projectmetis.models.TableTop;
import com.example.projectmetis.models.User;
import lombok.Data;

import java.util.List;

@Data
public class TableDto {
    private Long id;
    private String article;
    private Long timeAssembly;
    private List<Byte> qrCode;
    private TableTop tableTop;
    private Long marketPlaceId;
    private User user;

    public TableDto(){}

    public TableDto(Table table){
        id=table.getId();
        article=table.getArticle();
        timeAssembly=table.getTimeAssembly();
        qrCode=table.getQrCode();
        tableTop=table.getTableTop();
        marketPlaceId=table.getMarketPlaceId();
        user=table.getUser();
    }
}
