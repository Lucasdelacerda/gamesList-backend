package com.scrimet.dslist.entities;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_users")
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    public String id;
    public String userName;
    @Column(unique = true)
    public String email;
    @Column(nullable = false)
    public String password;
}
