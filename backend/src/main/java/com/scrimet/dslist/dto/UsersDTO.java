package com.scrimet.dslist.dto;

import com.scrimet.dslist.entities.Users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersDTO {
    public String name;
    public String email;
    public String password;

    public UsersDTO(Users entity) {
        name = entity.getName();
        email = entity.getEmail();
        password = entity.getPassword();
    }
}
