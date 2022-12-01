/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.repository;

import com.jkalvered.core.entite.Bedikot;
import com.jkalvered.core.entite.HefsekTahara;
import com.jkalvered.core.entite.MohDahouk;
import com.jkalvered.core.entite.Niddah;
import com.jkalvered.core.entite.Purification;
import com.jkalvered.core.entite.Tevila;
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

    public MohDahouk getMohDaoukByIdAndIdFemme(Long idMohDahouk, Long idFemme) {

        String query = "From MohDahouk where id=:i and purification.niddah.personne.id=:j";
        Query queryHibernate = this.sessionFactory.getCurrentSession().createQuery(query);
        queryHibernate.setParameter("i", idMohDahouk);
        queryHibernate.setParameter("j", idFemme);
        MohDahouk res = (MohDahouk) DataAccessUtils.singleResult(queryHibernate.getResultList());
        return res;
    }

    public Bedikot getBedikotByIdAndIdFemme(Long idBedika, Long idFemme) {
        String query = "From Bedikot where id=:i and chevaNekiym.purification.niddah.personne.id=:j";
        Query queryHibernate = this.sessionFactory.getCurrentSession().createQuery(query);
        queryHibernate.setParameter("i", idBedika);
        queryHibernate.setParameter("j", idFemme);
        Bedikot res = (Bedikot) DataAccessUtils.singleResult(queryHibernate.getResultList());
        return res;
    }

    public void deleteBedikotNull(Long idChevaNekiym) {

        String query = "Delete From Bedikot where chevaNekiym.id=:i and etatBedika1 is null and etatBedika2 is null";
        Query queryHibernate = this.sessionFactory.getCurrentSession().createQuery(query);
        queryHibernate.setParameter("i", idChevaNekiym);
        queryHibernate.executeUpdate();
    }

    public Purification getPurificationByIdAndIdFemme(Long idPurification, Long idFemme) {
        String query = "From Purification where id=:i and niddah.personne.id=:j";
        Query queryHibernate = this.sessionFactory.getCurrentSession().createQuery(query);
        queryHibernate.setParameter("i", idPurification);
        queryHibernate.setParameter("j", idFemme);
        Purification res = (Purification) DataAccessUtils.singleResult(queryHibernate.getResultList());
        return res;
    }

    public Tevila getTevilaByIdAndIdFemme(Long idTevila, Long idFemme) {
        String query = "From Tevila where id=:i and purification.niddah.personne.id=:j";
        Query queryHibernate = this.sessionFactory.getCurrentSession().createQuery(query);
        queryHibernate.setParameter("i", idTevila);
        queryHibernate.setParameter("j", idFemme);
        Tevila res = (Tevila) DataAccessUtils.singleResult(queryHibernate.getResultList());
        return res;
    }

    public Niddah getNiddahByIdAndIdFemme(Long idNiddah, Long idFemme) {
        String query = "From Niddah where id=:i and personne.id=:j";
        Query queryHibernate = this.sessionFactory.getCurrentSession().createQuery(query);
        queryHibernate.setParameter("i", idNiddah);
        queryHibernate.setParameter("j", idFemme);
        Niddah res = (Niddah) DataAccessUtils.singleResult(queryHibernate.getResultList());
        return res;
    }

}
