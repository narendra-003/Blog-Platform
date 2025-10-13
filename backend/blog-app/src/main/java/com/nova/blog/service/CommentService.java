package com.nova.blog.service;

import com.nova.blog.payload.CommentDTO;

public interface CommentService {

    CommentDTO createComment(CommentDTO commentDTO, Integer postID);

    void deleteComment(Integer commentID);
}
