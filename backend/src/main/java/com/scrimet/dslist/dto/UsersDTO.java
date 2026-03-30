package com.scrimet.dslist.dto;

import com.scrimet.dslist.entities.Users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersDTO {
    public String userName;
    public String email;
    public String password;

    public UsersDTO(Users entity) {
        userName = entity.getUserName();
        email = entity.getEmail();
        password = entity.getPassword();
    }
}
