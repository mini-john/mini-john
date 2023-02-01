/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.web.controller.tool;

import java.util.List;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 *
 * @author jonat
 */
public class BindingResultTools {
    
    
    public static BindingResult deleteDuplicateError(BindingResult bd,Object modelAttribute,String name){
         List<ObjectError> errors = bd.getAllErrors();
         BindingResult bdRes=new BeanPropertyBindingResult(modelAttribute, name);
         for(ObjectError error : errors){
             if(!bdRes.getAllErrors().contains(error)){
                 bdRes.addError(error);
             }
         }
        // bd.;
        return bdRes;
    }
}
