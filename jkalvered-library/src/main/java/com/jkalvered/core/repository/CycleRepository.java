/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.repository;

import com.jkalvered.core.entite.Cycle;
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

}
