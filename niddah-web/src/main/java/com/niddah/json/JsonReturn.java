/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.json;

import com.fasterxml.jackson.annotation.JsonView;

/**
 *
 * @author Boccara Jonathan
 */
public class JsonReturn {

    @JsonView(Views.Public.class)
    String msg;

    @JsonView(Views.Public.class)
    String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
