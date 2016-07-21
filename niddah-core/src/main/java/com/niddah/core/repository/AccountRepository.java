/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.repository;

import com.niddah.core.entity.Account;
import java.io.Serializable;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mini-john
 */
@Repository
public class AccountRepository extends AbstractDao<Serializable, Account>  {
    public void addAccount(Account account){
        this.getSession().persist(account);
        
    }

    public Account findByUserName(String userName) {
        
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("logine", userName));
        return (Account) crit.uniqueResult();
       }
}
