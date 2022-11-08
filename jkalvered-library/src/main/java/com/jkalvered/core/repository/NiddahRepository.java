/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.repository;

import com.jkalvered.core.entite.Niddah;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jonat
 */
@Repository
public class NiddahRepository extends CrudRepository {

    public Niddah getDernierNiddah(int idFemme) {
        String query = "From Niddah where personne.id=:i order bu id desc";
        return (Niddah) this.sessionFactory.getCurrentSession().createQuery(query).setParameter("i", idFemme).setMaxResults(1).getSingleResult();

    }

}
