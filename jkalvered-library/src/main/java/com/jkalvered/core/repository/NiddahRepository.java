/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.repository;

import com.jkalvered.core.entite.HefsekTahara;
import com.jkalvered.core.entite.Niddah;
import java.util.Collection;
import org.hibernate.query.Query;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jonat
 */
@Repository
public class NiddahRepository extends CrudRepository {

    public Niddah getDernierNiddah(Long idFemme) {
        String query = "From Niddah where personne.id=:i order by id desc";
        return (Niddah) DataAccessUtils.singleResult(this.sessionFactory.getCurrentSession().createQuery(query).setParameter("i", idFemme).setMaxResults(1).getResultList());

    }

    public HefsekTahara getHefsekTaharaByIdAndIdFemme(Long idHt, Long idFemme) {
        String query = "From HefsekTahara where id=:i and purification.niddah.personne.id=:j";
        Query queryHibernate = this.sessionFactory.getCurrentSession().createQuery(query);
        queryHibernate.setParameter("i", idHt);
        queryHibernate.setParameter("j", idFemme);
        HefsekTahara res = (HefsekTahara) DataAccessUtils.singleResult(queryHibernate.getResultList());
        return res;
    }

}
