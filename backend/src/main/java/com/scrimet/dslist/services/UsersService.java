package com.scrimet.dslist.services;

import com.scrimet.dslist.dto.UsersPanelDTO;
import com.scrimet.dslist.entities.Users;
import com.scrimet.dslist.repositories.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UsersService {
    private UsersRepository usersRepository;

    @Transactional(readOnly = true)
    public List<UsersPanelDTO> searchByEmail(String email) {
    List<Users> result = usersRepository.searchByEmail(email);

    return result.stream().map(x -> new UsersPanelDTO(x)).toList();
    }
}
