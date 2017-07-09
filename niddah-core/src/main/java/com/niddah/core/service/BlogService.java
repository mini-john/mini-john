/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.service;

import com.niddah.core.entity.blog.Comments;
import com.niddah.core.entity.blog.Post;
import com.niddah.core.repository.BlogRepository;
import com.niddah.library.dto.blog.CommentsDto;
import com.niddah.library.dto.blog.PostDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Boccara Jonathan
 */
@Service
@Transactional
public class BlogService extends CrudService {

    @Autowired
    private BlogRepository blogRepository;

    public List<PostDto> allPostWithPagination(Integer offset, Integer maxResults) {
        List<Post> list = blogRepository.allPostWithPagination(offset, maxResults);
        return this.niddahCastor.convertList(list, PostDto.class);
    }

    public Long count() {
        return blogRepository.count();
    }

    public PostDto getFirstPost() {
        Post post = blogRepository.getFirstPost();
        return this.niddahCastor.convert(post, PostDto.class);
    }

    public List<CommentsDto> allCommentWithPagination(Long id, Integer offset, Integer maxResults) {
        List<Comments> list = blogRepository.allCommentsWithPagination(id, offset, maxResults);
        return this.niddahCastor.convertList(list, CommentsDto.class);
    }

    public Long countComment(Long id) {
        return blogRepository.countComment(id);
    }

    public List<CommentsDto> getLastFourComment() {
        List<Comments> list = blogRepository.getLastFourComment();
        return this.niddahCastor.convertList(list, CommentsDto.class);
    }

}
