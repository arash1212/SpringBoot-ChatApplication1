package com.example.springbootchatapplication1.model.repository;

import com.example.springbootchatapplication1.model.entity.interfaces.IEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class GenericRepository<T extends IEntity> {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    public abstract Class<T> getDomainClass();

    public Long save(T t) {
        this.entityManager.persist(t);
        return t.getId();
    }

    public T update(T t) {
        this.entityManager.merge(t);
        return t;
    }

    public void delete(T t) {
        this.entityManager.remove(t);
    }

    public Optional<T> findById(Long id) {
        T t = this.entityManager.find(this.getDomainClass(), id);
        return Optional.of(t);
    }

    public Optional<T> find(Map<String, Object> queryParams) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.getDomainClass());
        Root<T> root = criteriaQuery.from(this.getDomainClass());
        criteriaQuery.select(root).where(this.getPredicates(queryParams, criteriaBuilder, root));

        TypedQuery<T> query = this.entityManager.createQuery(criteriaQuery);
        return Optional.of(query.getSingleResult());
    }

    public List<T> findAll() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.getDomainClass());
        Root<T> root = criteriaQuery.from(this.getDomainClass());
        criteriaQuery.select(root);

        TypedQuery<T> query = this.entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<T> findAll(List<Long> ids) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.getDomainClass());
        Root<T> root = criteriaQuery.from(this.getDomainClass());
        criteriaQuery.select(root).where(root.get("id").in(ids));

        TypedQuery<T> query = this.entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /******************************************************************************************************************/

    //TODO
    private Predicate[] getPredicates(Map<String, Object> queryParams, CriteriaBuilder criteriaBuilder, Root<T> root) {
        List<Predicate> predicates = new ArrayList<>(queryParams.size());

        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            if (entry.getValue() instanceof String) {
                predicates.add(criteriaBuilder.like(root.get(entry.getKey()), String.valueOf(entry.getValue())));
            } else {
                predicates.add(criteriaBuilder.equal(root.get(entry.getKey()), String.valueOf(entry.getValue())));
            }
        }

        return predicates.toArray(new Predicate[0]);
    }
}
