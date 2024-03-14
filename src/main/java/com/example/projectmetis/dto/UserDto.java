package com.example.projectmetis.dto;

import com.example.projectmetis.models.User;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;

    public UserDto(User user){
        id = user.getId();
        name = user.getName();
    }
}
