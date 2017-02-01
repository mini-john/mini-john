/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.castor;

import com.niddah.core.entity.Cycle;
import com.niddah.library.dto.CycleDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author mini-john
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.niddah.configuration.test.AppConfigCore.class}, loader=AnnotationConfigContextLoader.class)
public  class TestNiddahCastor  {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestNiddahCastor.class);

    @Autowired
    NiddahCastor niddahCastor;

    public TestNiddahCastor() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
   @Test
    public void testCastor() throws NoSuchMethodException {
        Cycle cycle = new Cycle();
        cycle.setId(1L);
        
        System.out.println("com.niddah.core.castor.TestNiddahCastor.testCastor()");
        
    }
}
