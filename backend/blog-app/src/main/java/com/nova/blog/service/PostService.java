package com.nova.blog.service;

import com.nova.blog.payload.PostDTO;

import java.util.List;

public interface PostService {

    // create
    PostDTO createPost(PostDTO postDTO, Integer userID, Integer categoryID);

    // update
    PostDTO updatePost(PostDTO postDTO, Integer postID);

    // delete
    void deletePost(Integer postID);

    // get all posts
    List<PostDTO> getAllPosts();

    // get single post
    PostDTO getPostByID(Integer postID);

    // get all post by category
    List<PostDTO> getPostsByCategory(Integer categoryID);

    // get all post by user
    List<PostDTO> getPostsByUser(Integer userID);
}
