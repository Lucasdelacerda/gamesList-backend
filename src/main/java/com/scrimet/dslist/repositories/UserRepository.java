package com.scrimet.dslist.repositories;

import com.scrimet.dslist.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM tb_users WHERE LOWER(email) LIKE LOWER(CONCAT('%', :email, '%'))")
    List<User> searchByEmail(String email);
    
    @Query(nativeQuery = true, value = "SELECT * FROM tb_users WHERE email = :email")
    Optional<User> findByEmail(String email);
}
