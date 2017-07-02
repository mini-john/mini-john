/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.library.dto.blog;

import java.util.Date;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Boccara Jonathan
 */
public class CommentsDto {

    private Long id;
    @Size(min = 1)
    private String autheur;
    @Size(min = 1)
    private String commentaire;
    @Email
    private String email;
    private Date dateComments;
    private PostDto post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutheur() {
        return autheur;
    }

    public void setAutheur(String autheur) {
        this.autheur = autheur;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public PostDto getPost() {
        return post;
    }

    public void setPost(PostDto post) {
        this.post = post;
    }

    public Date getDateComments() {
        return dateComments;
    }

    public void setDateComments(Date dateComments) {
        this.dateComments = dateComments;
    }

}
