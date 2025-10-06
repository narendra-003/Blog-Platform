package com.nova.blog.controller;

import com.nova.blog.config.AppConstants;
import com.nova.blog.payload.ApiResponse;
import com.nova.blog.payload.PostDTO;
import com.nova.blog.payload.PostResponse;
import com.nova.blog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create
    @PostMapping("/user/{userID}/category/{categoryID}")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userID, @PathVariable Integer categoryID) {
        PostDTO postResponseDTO = postService.createPost(postDTO, userID, categoryID);
        return ResponseEntity.ok().body(postResponseDTO);
    }

    // update post
    @PutMapping("/{postID}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Integer postID) {

        PostDTO responsePostDTO = postService.updatePost(postDTO, postID);
        return ResponseEntity.ok().body(responsePostDTO);
    }

    // delete post by id
    @DeleteMapping("/{postID}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postID) {

        postService.deletePost(postID);
        return ResponseEntity.ok().body(new ApiResponse("Post deleted successfully", true));
    }

    // get all posts
    @GetMapping("/")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
    ) {

        PostResponse postResponse = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return ResponseEntity.ok().body(postResponse);
    }

    // get post by id
    @GetMapping("/{postID}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postID) {

        PostDTO postDTO = postService.getPostByID(postID);
        return ResponseEntity.ok().body(postDTO);
    }

    // get by user
    @GetMapping("/user/{userID}")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userID) {

        List<PostDTO> posts = postService.getPostsByUser(userID);
        return ResponseEntity.ok().body(posts);
    }

    // get by category
    @GetMapping("/category/{categoryID}")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryID) {

        List<PostDTO> posts = postService.getPostsByCategory(categoryID);
        return ResponseEntity.ok().body(posts);
    }

    // search
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDTO>> searchPostByTitle(
            @PathVariable("keyword") String keyword
    ) {
        List<PostDTO> postDTOS = postService.searchPosts(keyword);
        return ResponseEntity.ok().body(postDTOS);
    }

}
