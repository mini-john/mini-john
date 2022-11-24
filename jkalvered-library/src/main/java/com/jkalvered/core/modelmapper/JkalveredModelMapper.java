/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.core.modelmapper;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

/**
 *
 * @author mini-john
 */
@Getter
@Setter
public class JkalveredModelMapper {

    ModelMapper  castorMarshaller;
    

    public <T> T convert(Object object, Class destination) {
        if (object == null) {
            return null;
        }
        return (T) castorMarshaller.map(object, destination);
    }

    public <T> List convertList(List objectList, Class destination) {
        List<T> tmpList = new ArrayList();
        objectList.stream().filter((object) -> (object != null)).forEachOrdered((object) -> {
            tmpList.add((T) castorMarshaller.map(object, destination));
        });

        return tmpList;
    }

}
