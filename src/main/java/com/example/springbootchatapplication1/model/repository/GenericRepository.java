package com.example.springbootchatapplication1.model.repository;

import com.example.springbootchatapplication1.model.entity.interfaces.IEntity;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class GenericRepository<T extends IEntity> {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    protected EntityManager entityManager;

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

    public void update(Long id, Map<String, Object> updateParams) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaUpdate<T> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(this.getDomainClass());
        Root<T> root = criteriaUpdate.from(this.getDomainClass());
        for (Map.Entry<String, Object> entry : updateParams.entrySet()) {
            criteriaUpdate.set(entry.getKey(), entry.getValue());
        }
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), id));

        Query query = this.entityManager.createQuery(criteriaUpdate);
        query.executeUpdate();
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

    public List<T> selectQuery(String query, Map<String, Object> params) {;
        TypedQuery<T> typedQuery = (TypedQuery<T>) this.entityManager.createQuery(query);
        for (Map.Entry entry : params.entrySet()) {
            typedQuery.setParameter(String.valueOf(entry.getKey()), entry.getValue());
        }
        return typedQuery.getResultList();
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
