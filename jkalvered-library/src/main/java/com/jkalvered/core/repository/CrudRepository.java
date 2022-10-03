package com.jkalvered.core.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
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
        return sessionFactory.getCurrentSession().createCriteria(entity).addOrder(Order.asc("id")).list();
    }

    public <T> List<T> findByCriteria(Class entity, Criterion criterion) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entity);
        criteria.add(criterion);
        return criteria.list();

    }

    protected Criteria createEntityCriteria(Class entity) {
        return getSession().createCriteria(entity);
    }

}
