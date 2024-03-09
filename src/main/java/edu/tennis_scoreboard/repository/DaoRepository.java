package edu.tennis_scoreboard.repository;

import java.util.List;

public interface DaoRepository<E> {

    E get(Long id);

    E save(E entity);

    List<E> findAll();
}
