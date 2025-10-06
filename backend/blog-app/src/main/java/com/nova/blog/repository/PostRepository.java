package com.nova.blog.repository;

import com.nova.blog.model.Category;
import com.nova.blog.model.Post;
import com.nova.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContainingIgnoreCase(String title);
}
