/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.castor;

import javax.annotation.PostConstruct;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author mini-john
 */
@Component
public class NiddahCastor {

    @Autowired
    Mapper castorMarshaller;

    public <T> T convert(Object object,Class destination)  {
        return (T) castorMarshaller.map(object, destination);
    }

    @PostConstruct
    public void test() {
        System.out.println("com.niddah.core.castor.NiddahCastor.test() je suis construit");
    }

}
