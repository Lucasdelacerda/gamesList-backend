package com.scrimet.dslist.services;

import com.scrimet.dslist.dto.UsersDTO;
import com.scrimet.dslist.entities.Users;
import com.scrimet.dslist.repositories.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Transactional(readOnly = true)
    public List<UsersDTO> searchByEmail(String email) {
    List<Users> result = usersRepository.searchByEmail(email);

    return result.stream().map(x -> new UsersDTO(x)).toList();
    }
    @Transactional
    public UsersDTO insert(UsersDTO dto){
        Users entity = new Users();
        entity.setUserName(dto.getUserName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity = usersRepository.save(entity);
        return new UsersDTO(entity);}
}
