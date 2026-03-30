package com.scrimet.dslist.services;

import com.scrimet.dslist.dto.UserDTO;
import com.scrimet.dslist.entities.User;
import com.scrimet.dslist.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UserRepository usersRepository;

    @Transactional(readOnly = true)
    public List<UserDTO> searchByEmail(String email) {
    List<User> result = usersRepository.searchByEmail(email);

    return result.stream().map(x -> new UserDTO(x)).toList();
    }
    @Transactional
    public UserDTO insert(UserDTO dto){
        User entity = new User();
        entity.setUserName(dto.getUserName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity = usersRepository.save(entity);
        return new UserDTO(entity);}
}
