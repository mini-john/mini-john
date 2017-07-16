/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.repository;

import com.niddah.core.entity.blog.Comments;
import com.niddah.core.entity.blog.Post;
import com.niddah.library.constante.Constantes;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Boccara Jonathan
 */
@Repository
public class BlogRepository extends CrudRepository {

    @Autowired
    SessionFactory session;

    public List<Post> allPostWithPagination(Integer offset, Integer maxResults) {
        Criteria crit = createEntityCriteria(Post.class);
        crit.addOrder(Property.forName("date").asc());
        crit.setFirstResult(offset != null ? offset : 0);
        crit.setMaxResults(maxResults != null ? maxResults : Constantes.DEFAULT_MAX_PAGINATION);
        return crit.list();
    }

    public Long count() {
        Criteria crit = createEntityCriteria(Post.class);
        return (Long) crit
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    public Post getFirstPost() {
        Criteria crit = createEntityCriteria(Post.class);
        crit.addOrder(Property.forName("date").desc()).setFirstResult(0).setMaxResults(1);
        return (Post) crit.uniqueResult();
    }

    public List<Comments> allCommentsWithPagination(Long id, Integer offset, Integer maxResults) {
        Criteria crit = createEntityCriteria(Comments.class);
        crit.add(Restrictions.eq("post.id", id));
        crit.addOrder(Property.forName("dateComments").asc());
        crit.setFirstResult(offset != null ? offset : 0);
        crit.setMaxResults(maxResults != null ? maxResults : Constantes.DEFAULT_MAX_PAGINATION);
        return crit.list();
    }

    public Long countComment(Long id) {
        Criteria crit = createEntityCriteria(Comments.class);
        crit.add(Restrictions.eq("post.id", id));
        return (Long) crit
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    public List<Comments> getLastFourComment() {
        Criteria crit = createEntityCriteria(Comments.class);
        crit.addOrder(Property.forName("dateComments").desc());

        crit.setMaxResults(4);
        return crit.list();
    }

}
