/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.entity.blog;

import com.niddah.core.entity.Account;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Boccara Jonathan
 */
@Entity
@Table(name = "Posts")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "post_sequence")
    @SequenceGenerator(name = "post_sequence", sequenceName = "post_sequence")
    private Long id;

    @Column(nullable = false, length = 300)
    private String title;

    @Column(nullable = false, length = 9000000)
    private String body;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Account author;
    @OneToMany(mappedBy = "post",cascade = javax.persistence.CascadeType.REMOVE)
    @OrderBy("dateComments ASC")
    private List<Comments> commentss;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
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

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   
    public List<Comments> getCommentss() {
        return commentss;
    }

    public void setCommentss(List<Comments> commentss) {
        this.commentss = commentss;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", title=" + title + ", body=" + body /*+ ", author=" + author */ + ", date=" + date + '}';
    }

}
