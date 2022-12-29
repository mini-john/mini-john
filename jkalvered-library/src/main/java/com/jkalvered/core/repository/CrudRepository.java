package com.jkalvered.core.repository;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mini-john
 */
@Repository
public class CrudRepository {

    @Autowired
    SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public <T> void persist(T entity) {
        sessionFactory.getCurrentSession().persist(entity);

    }

    public <T> T merge(T entity) {
        return (T) sessionFactory.getCurrentSession().merge(entity);

    }

    public <T> void update(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);

    }

    public <T> void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);

    }

    public <T> T findById(Class entity, Long id) {

        return (T) sessionFactory.getCurrentSession().get(entity, id);

    }

    public <T> List<T> findAll(Class entity) {
        CriteriaBuilder cb = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entity);
        Root<T> root = cq.from(entity);
        cq.orderBy(cb.asc(root.get("id")));
        return sessionFactory.getCurrentSession().createQuery(cq).getResultList();
    }
  
}
