package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.entity.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost,Long>{

}
