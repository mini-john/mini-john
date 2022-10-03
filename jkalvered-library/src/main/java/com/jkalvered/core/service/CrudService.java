/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.core.service;

import com.jkalvered.core.castor.NiddahCastor;
import com.jkalvered.core.repository.CrudRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger LOGGER = LogManager.getLogger();

    public <T> T add(T entityDTO, Class entity) {

        T object = (T) niddahCastor.convert(entityDTO, entity);
        crudRepository.persist(object);
        return niddahCastor.convert(object, entityDTO.getClass());

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
