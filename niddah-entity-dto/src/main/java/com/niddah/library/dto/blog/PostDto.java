/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.library.dto.blog;

import com.niddah.library.dto.AccountDto;
import java.util.Date;

/**
 *
 * @author Boccara Jonathan
 */
public class PostDto  {

    private Long id;

    private String title;

    private String body;

   // private AccountDto author;

    private Date date = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

//    public AccountDto getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(AccountDto author) {
//        this.author = author;
//    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", title=" + title + ", body=" + body + ", author=" +/* author + */", date=" + date + '}';
    }
    
}
