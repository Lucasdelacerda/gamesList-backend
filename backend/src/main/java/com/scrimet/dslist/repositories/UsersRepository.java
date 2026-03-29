package com.scrimet.dslist.repositories;

import com.scrimet.dslist.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM tb_users WHERE LOWER(email) LIKE LOWER(CONCAT('%', :email, '%'))")
    List<Users> searchByEmail(String email);

}
