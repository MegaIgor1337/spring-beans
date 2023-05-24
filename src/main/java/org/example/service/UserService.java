package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.CompanyReadDto;
import org.example.dto.UserReadDto;
import org.example.repo.UserRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    public Optional<UserReadDto> findById(Session session, Long id) {
        return userRepository.find(session, id).map(it -> new UserReadDto(it.getId(),
                it.getPersonalInfo(), it.getUsername(), it.getRole(),
                new CompanyReadDto(it.getCompany().getId(), it.getCompany().getName())));
    }
}
