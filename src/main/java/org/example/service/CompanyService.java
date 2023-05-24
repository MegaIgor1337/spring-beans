package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.CompanyReadDto;
import org.example.repo.CompanyRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    public Optional<CompanyReadDto> findById(Session session, Integer id) {
        return companyRepository.find(session, id).map(it -> new CompanyReadDto(it.getId(), it.getName()));
    }
}
