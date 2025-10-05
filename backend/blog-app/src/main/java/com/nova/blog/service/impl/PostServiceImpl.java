package com.nova.blog.service.impl;

import com.nova.blog.exception.ResourceNotFoundException;
import com.nova.blog.model.Category;
import com.nova.blog.model.Post;
import com.nova.blog.model.User;
import com.nova.blog.payload.PostDTO;
import com.nova.blog.payload.PostResponse;
import com.nova.blog.repository.CategoryRepository;
import com.nova.blog.repository.PostRepository;
import com.nova.blog.repository.UserRepository;
import com.nova.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userID, Integer categoryID) {

        User user = userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userID));

        Category category = categoryRepository.findById(categoryID).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryID));

        Post post = modelMapper.map(postDTO, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postID) {

        Post post = postRepository.findById(postID).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postID));

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());

        Post updatedPost = postRepository.save(post);
        return modelMapper.map(updatedPost, PostDTO.class);
    }

    @Override
    public void deletePost(Integer postID) {

        Post post = postRepository.findById(postID).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postID));

        postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePost = postRepository.findAll(p);

        List<Post> posts = pagePost.getContent();

        List<PostDTO> postDTOS = posts.stream().map((post) -> modelMapper.map(post, PostDTO.class)).toList();

        // prepare response
        PostResponse postResponse = new PostResponse();

        // content
        postResponse.setData(postDTOS);

        // pagination-metadata
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDTO getPostByID(Integer postID) {

        Post post = postRepository.findById(postID).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postID));
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> getPostsByCategory(Integer categoryID) {

        Category category = categoryRepository.findById(categoryID).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryID));

        List<Post> posts = postRepository.findByCategory(category);
        List<PostDTO> postDTOS = posts.stream().map((post) -> modelMapper.map(post, PostDTO.class)).toList();
        return postDTOS;
    }

    @Override
    public List<PostDTO> getPostsByUser(Integer userID) {

        User user = userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userID));

        List<Post> posts = postRepository.findByUser(user);
        List<PostDTO> postDTOS = posts.stream().map((post) -> modelMapper.map(post, PostDTO.class)).toList();
        return postDTOS;
    }
}
