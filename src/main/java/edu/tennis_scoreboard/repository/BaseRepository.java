package edu.tennis_scoreboard.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@RequiredArgsConstructor
public abstract class BaseRepository<E> implements DaoRepository<E> {

    private final Class<E> clazz;
    protected final Session session;

    @Override
    public E get(Long id) {
        return session.get(clazz, id);
    }

    @Override
    public E save(E entity) {
        session.persist(entity);
        return entity;
    }

    @Override
    public List<E> findAll() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<E> criteria = cb.createQuery(clazz);
        Root<E> entity = criteria.from(clazz);
        criteria.select(entity);
        return session.createQuery(criteria).list();
    }
}
