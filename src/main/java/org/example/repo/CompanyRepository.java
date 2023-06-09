package org.example.repo;

import lombok.AllArgsConstructor;
import org.example.connection.ConnectionDataBase;
import org.example.entity.Company;
import org.example.entity.User;
import org.hibernate.Session;

import java.util.Optional;

@AllArgsConstructor
public class CompanyRepository implements Dao<Integer, Company> {
    private ConnectionDataBase connectionDataBase;

    @Override
    public Optional<Company> find(Session session, Integer id) {
        return Optional.ofNullable(session.get(Company.class, id));
    }
}
