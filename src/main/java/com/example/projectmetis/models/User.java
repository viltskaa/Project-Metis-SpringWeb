package com.example.projectmetis.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class User {
    @Id
    private Long id;
    @Column
    private String name;
    private List<Integer> work;
    private List<Integer> tables;
    public User(){}

}
