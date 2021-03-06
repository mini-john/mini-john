/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.service;

import com.niddah.core.entity.Account;
import com.niddah.core.repository.AccountRepository;
import com.niddah.library.dto.AccountDto;
import java.util.List;
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

   
    public AccountDto findByUserName(String userName) {
       return  niddahCastor.convert(accountRepository.findByUserName(userName), AccountDto.class);
    }

    public List<AccountDto> allUserWithPagination(Integer offset, Integer maxResults) {
        List<Account> list = accountRepository.allUserWithPagination(offset, maxResults);
        return this.niddahCastor.convertList(list, AccountDto.class);
    }

    public Long countUser() {
        return accountRepository.countUser();
    }
    
    
}
