package com.example.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.blog.entity.BlogPost;
import com.example.blog.repository.BlogPostRepository;

@Controller
public class BlogController {
	
	@Autowired
	private BlogPostRepository repo;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("posts",repo.findAll());
		return "index";
	}
	
	@GetMapping("/new")
	public String createForm(Model model) 
	{
		model.addAttribute("post",new BlogPost());
		return "create";
	}
	
	@PostMapping("/save")
	public String savePost(@ModelAttribute BlogPost post)
	{
		repo.save(post);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) 
	{
		repo.deleteById(id);
		return "redirect:/";
	}
	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable Long id, Model model) {
	    BlogPost post = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + id));
	    model.addAttribute("post", post);
	    return "edit"; // this should match your edit.html
	}
	
	@PostMapping("/update")
	public String updatePost(@ModelAttribute BlogPost post) {
	    repo.save(post); // JPA will update if ID is present
	    return "redirect:/";
	}

	
	//REST API
	@GetMapping("/api/posts")
	@ResponseBody
	public Iterable<BlogPost> getPostsApi(){
		return repo.findAll();
	}
	
}
