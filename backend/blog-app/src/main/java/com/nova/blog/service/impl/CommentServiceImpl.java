package com.nova.blog.service.impl;

import com.nova.blog.exception.ResourceNotFoundException;
import com.nova.blog.model.Comment;
import com.nova.blog.model.Post;
import com.nova.blog.payload.CommentDTO;
import com.nova.blog.repository.CommentRepository;
import com.nova.blog.repository.PostRepository;
import com.nova.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer postID) {

        Post post = postRepository.findById(postID).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postID));

        Comment comment = modelMapper.map(commentDTO, Comment.class);
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentID) {

        Comment comment = commentRepository.findById(commentID).orElseThrow(() -> new ResourceNotFoundException("Comment", "commend id", commentID));

        commentRepository.delete(comment);
    }
}
