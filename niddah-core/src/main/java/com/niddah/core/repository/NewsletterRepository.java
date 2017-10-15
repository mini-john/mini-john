/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.repository;

import com.niddah.core.entity.Newsletter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Boccara Jonathan
 */
@Repository
public class NewsletterRepository extends CrudRepository {

    public Newsletter emailExist(String email) {
        Criteria crit = createEntityCriteria(Newsletter.class);
        crit.add(Restrictions.eq("email", email));
        return (Newsletter) crit.uniqueResult();
    }
}
