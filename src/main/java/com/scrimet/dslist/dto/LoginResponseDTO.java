package com.scrimet.dslist.dto;

import com.scrimet.dslist.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    public String userName;
    public String email;
    public String token;

    public LoginResponseDTO(User user, String token) {
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.token = token;
    }
}
