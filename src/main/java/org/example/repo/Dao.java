package org.example.repo;

import org.hibernate.Session;

import java.util.Optional;

public interface Dao<K, E> {
    Optional<E> find(Session session, K k);
}
