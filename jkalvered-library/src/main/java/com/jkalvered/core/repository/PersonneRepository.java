/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.repository;

import com.jkalvered.core.entite.Account;
import com.jkalvered.core.entite.Personne;
import org.hibernate.query.Query;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jonat
 */
@Repository
public class PersonneRepository extends CrudRepository {

    public Personne getByLogin(String login) {
        String query = "From Personne where account.login= :login";
        Query queryHibernate = this.sessionFactory.getCurrentSession().createQuery(query);
        queryHibernate.setParameter("login", login);

        return (Personne) DataAccessUtils.singleResult(queryHibernate.getResultList());

    }
    public Account getAccountByLogin(String login) {
        String query = "From Account where login= :login";
        Query queryHibernate = this.sessionFactory.getCurrentSession().createQuery(query);
        queryHibernate.setParameter("login", login);

        return (Account) DataAccessUtils.singleResult(queryHibernate.getResultList());

    }

}
