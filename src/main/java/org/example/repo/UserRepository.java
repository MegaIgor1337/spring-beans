package org.example.repo;

import lombok.AllArgsConstructor;
import org.example.connection.ConnectionDataBase;
import org.example.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class UserRepository implements Dao<Long, User> {
    private ConnectionDataBase connectionDataBase;

    @Override
    public Optional<User> find(Session session, Long id) {
        return Optional.ofNullable(session.get(User.class, id));
    }
}
