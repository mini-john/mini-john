/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.repository;

import com.niddah.core.entity.Account;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mini-john
 */
@Repository
public class AccountRepository extends CrudRepository {

    public Account findByUserName(String userName) {

        Criteria crit = createEntityCriteria(Account.class);
        crit.add(Restrictions.eq("login", userName));
        return (Account) crit.uniqueResult();
    }

    public List<Account> allUserWithPagination(Integer offset, Integer maxResults) {
        Criteria crit = createEntityCriteria(Account.class);
        crit.addOrder(Property.forName("id").asc());
        crit.setFirstResult(offset != null ? offset : 0);
        crit.setMaxResults(maxResults != null ? maxResults : 10);
        return crit.list();
    }

    public Long countUser() {
        Criteria crit = createEntityCriteria(Account.class);
        return (Long) crit
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }
}
