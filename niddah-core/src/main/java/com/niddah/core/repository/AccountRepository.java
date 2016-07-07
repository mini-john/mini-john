/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.repository;

import com.niddah.core.entity.Account;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mini-john
 */
@Repository
public class AccountRepository extends AbstractDao<Integer, Account>  {
    public void addAccount(Account account){
        this.getSession().persist(account);
        
    }
}
