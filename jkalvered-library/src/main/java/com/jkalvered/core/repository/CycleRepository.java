/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.repository;

import com.jkalvered.core.entite.Cycle;
import com.jkalvered.core.entite.HefsekTahara;
import org.hibernate.query.Query;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jonat
 */
@Repository
public class CycleRepository extends CrudRepository {
    
    public Cycle getDernierCycle(Long idFemme) {
        String query = "From Cycle where personne.id=:i order by id desc";
        return (Cycle) DataAccessUtils.singleResult(this.sessionFactory.getCurrentSession().createQuery(query).setParameter("i", idFemme).setMaxResults(1).getResultList());

    }

    public Cycle getCycleByIdAndIdFemme(Long idCycle, Long idFemme) {
       String query = "From Cycle where id=:i and personne.id=:j";
        Query queryHibernate = this.sessionFactory.getCurrentSession().createQuery(query);
        queryHibernate.setParameter("i", idCycle);
        queryHibernate.setParameter("j", idFemme);
        Cycle res = (Cycle) DataAccessUtils.singleResult(queryHibernate.getResultList());
        return res; }

}
