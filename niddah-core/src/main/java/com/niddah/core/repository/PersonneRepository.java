/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.repository;

import com.niddah.core.entity.Account;
import com.niddah.core.entity.Personne;
import com.niddah.library.enumeration.EtatAccount;
import org.hibernate.Criteria;
import org.hibernate.ReplicationMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mini-john
 */
@Repository
public class PersonneRepository extends CrudRepository {
    
    public Personne getByEmail(String email) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Personne.class).createAlias("account", "account")
                .add(Restrictions.eq("account.mail", email));
        
        return (Personne) criteria.uniqueResult();
    }
    
    public Personne getByLogin(String login) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Personne.class).createAlias("account", "account")
                .add(Restrictions.like("account.login", login, MatchMode.START));
        
        return (Personne) criteria.uniqueResult();
    }
    
    public boolean isLoginActifExist(String login) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Personne.class).createAlias("account", "account")
                .add(Restrictions.like("account.login", login, MatchMode.START))
                .add(Restrictions.ne("account.etatAccount", EtatAccount.creation));
        
        return null != criteria.uniqueResult();
    }
    
    public boolean isMailActifExist(String mail) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Personne.class).createAlias("account", "account")
                .add(Restrictions.like("account.mail", mail, MatchMode.START))
                .add(Restrictions.ne("account.etatAccount", EtatAccount.creation));
        
        return null != criteria.uniqueResult();
    }
    
    public Personne isMailCreationExist(String mail) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Personne.class).createAlias("account", "account")
                .add(Restrictions.like("account.mail", mail, MatchMode.START))
                .add(Restrictions.eq("account.etatAccount", EtatAccount.creation));
        
        return (Personne) criteria.uniqueResult();
    }
    
    public Personne isLoginCreationExist(String login) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Personne.class).createAlias("account", "account")
                .add(Restrictions.like("account.login", login, MatchMode.START))
                .add(Restrictions.eq("account.etatAccount", EtatAccount.creation));
        
        return (Personne) criteria.uniqueResult();
    }
    
    public Personne mergeAdmin(Personne entity) {
        Personne entitySearch;
        entitySearch = (Personne) sessionFactory.getCurrentSession().get(Personne.class, entity.getId());
        if (entitySearch == null) {
            sessionFactory.getCurrentSession().replicate(entity, ReplicationMode.EXCEPTION);
        } else {
            entitySearch.setNom(entity.getNom());
            entitySearch.setPrenom(entity.getPrenom());
            entitySearch.getAccount().setLogin(entity.getAccount().getLogin());
            entitySearch.getAccount().setPassword(entity.getAccount().getPassword());
            this.update(entitySearch);
        }
        return entity;
    }
    
}
