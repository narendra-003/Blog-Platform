package com.nova.blog.service;

import com.nova.blog.payload.PostDTO;
import com.nova.blog.payload.PostResponse;

import java.util.List;

public interface PostService {

    // create
    PostDTO createPost(PostDTO postDTO, Integer userID, Integer categoryID);

    // update
    PostDTO updatePost(PostDTO postDTO, Integer postID);

    // delete
    void deletePost(Integer postID);

    // get all posts
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    // get single post
    PostDTO getPostByID(Integer postID);

    // get all post by category
    List<PostDTO> getPostsByCategory(Integer categoryID);

    // get all post by user
    List<PostDTO> getPostsByUser(Integer userID);

    // search posts
    List<PostDTO> searchPosts(String keyword);
}
