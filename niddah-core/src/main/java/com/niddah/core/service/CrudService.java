/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.service;


import com.niddah.core.castor.NiddahCastor;
import com.niddah.core.repository.CrudRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mini-john
 */
@Service
@Transactional
public class CrudService {

    @Autowired
    CrudRepository crudRepository;
    @Autowired
    NiddahCastor niddahCastor;
    private static final Logger logger = LoggerFactory.getLogger(CrudService.class);

    public <T> Long add(T entityDTO, Class entity) {

        T adherent = (T) niddahCastor.convert(entityDTO, entity);
        Long idAdherent = (Long) crudRepository.persist(adherent);
        
      
     
        return idAdherent;

    }
    

    public <T> T merge(T entityDTO, Class entity) {

        Object object = niddahCastor.convert(entityDTO, entity);
        object = crudRepository.merge(object);

        object = niddahCastor.convert(object, entityDTO.getClass());

        return (T) object;

    }

    public <T> void update(T entityDO, Class entity) {
        Object adherent = niddahCastor.convert(entityDO, entity);
        crudRepository.update(adherent);
    }

    public <T> void delete(T entityDO, Class entity) {
        Object adherent = niddahCastor.convert(entityDO, entity);
        crudRepository.delete(adherent);
    }

    public <T> T getById(Long id, Class entity, Class entityDTO) {

        Object object = crudRepository.findById(entity, id);
        if (object != null) {
            object = niddahCastor.convert(object, entityDTO);
        }
        return (T) object;
    }
     public <T> T getById(Long id, Class entity) {

        Object object = crudRepository.findById(entity, id);
        
        return (T) object;
    }

    public <T> List<T> findAll(Class entity, Class entityDTO) {
        List list = crudRepository.findAll(entity);
        List result = new ArrayList<>();
        for (Object objet : list) {
            Object dozerResult = niddahCastor.convert(objet, entityDTO);
            result.add(dozerResult);
        }
        return result;
    }

}