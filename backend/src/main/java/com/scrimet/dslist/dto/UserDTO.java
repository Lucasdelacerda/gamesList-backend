package com.scrimet.dslist.dto;

import com.scrimet.dslist.entities.Role;
import com.scrimet.dslist.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    public String userName;
    public String email;
    public String password;
    public Role role;

    public UserDTO(User entity) {
        userName = entity.getUserName();
        email = entity.getEmail();
        password = entity.getPassword();
        role = entity.getRole();
    }
}
