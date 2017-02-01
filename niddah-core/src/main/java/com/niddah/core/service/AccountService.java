/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.service;

import com.niddah.core.entity.Account;
import com.niddah.core.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mini-john
 */
@Service()
@Transactional
public class AccountService extends CrudService{

    @Autowired
    private AccountRepository accountRepository;

    public void AddAcount(Account account) {
        accountRepository.persist(account);
    }

    public Account findByUserName(String userName) {
        return accountRepository.findByUserName(userName);
    }

   
    
    
}
