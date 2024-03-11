package com.example.projectmetis.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Setter
@Getter
public class Table {
    @Id
    private long id;
    private String article;
    private long timeAssembly;
    private List<Byte> qrCode;
    private TableTop tableTop;
    private long marketPlaceId;

}
