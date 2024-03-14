package com.example.projectmetis.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Setter
@Getter
public class Table {
    @Id
    private Long id;
    private String article;
    private Long timeAssembly;
    private List<Byte> qrCode;
    private TableTop tableTop;
    private Long marketPlaceId;
    private User user;
    @DBRef
    private List<AdditionalParts> additionalParts;
}
