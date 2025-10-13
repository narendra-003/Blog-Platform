package com.nova.blog.controller;

import com.nova.blog.payload.ApiResponse;
import com.nova.blog.payload.CommentDTO;
import com.nova.blog.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/post/{postID}")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer postID) {

        CommentDTO commentResponseDTO = commentService.createComment(commentDTO, postID);
        return ResponseEntity.ok().body(commentResponseDTO);
    }

    @DeleteMapping("/{commentID}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentID) {

        commentService.deleteComment(commentID);
        return ResponseEntity.ok().body(new ApiResponse("Comment deleted successfully!", true));
    }
}
