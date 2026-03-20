package com.scrimet.dslist.dto;

import com.scrimet.dslist.entities.Users;

public class UsersPanelDTO {
    public String name;
    public String email;

    public UsersPanelDTO(Users entity) {
        name = entity.getName();
        email = entity.getEmail();
    }
}
