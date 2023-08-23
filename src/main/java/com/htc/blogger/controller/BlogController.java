package com.htc.blogger.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.htc.blogger.CustomException.ResourceNotFoundException;
import com.htc.blogger.entity.Blog;
import com.htc.blogger.repo.BlogRepository;

@RestController
@RequestMapping("/api")
public class BlogController {

	@Autowired
	BlogRepository blogRepository;
	
	private static final Logger logger=LoggerFactory.getLogger(BlogController.class);
	
	@PostMapping("/blog")
	public Blog createBlog(@RequestBody Blog blog) {
		if (blog.getCreatedAt() == null)
			blog.setCreatedAt(LocalDateTime.now());
		return blogRepository.save(blog);
	}




	@PutMapping("/blog/{id}")
	public Blog updateBlog(@PathVariable(value = "id") Long blogId, @RequestBody Blog blogDetails) {

		logger.info("id: "+blogId+" blog: "+blogDetails);
		
		Blog blog = blogRepository.findById(blogId)
				.orElseThrow(() -> new ResourceNotFoundException("Blog", "id", blogId));

		blog.setTitle(blogDetails.getTitle());
		blog.setContent(blogDetails.getContent());
		blog.setAuthorId(blogDetails.getAuthorId());
		blog.setUpdatedAt(LocalDateTime.now());

		Blog updatedBlog = blogRepository.save(blog);
		return updatedBlog;
	}

	/*
	 * 
	 * @GetMapping("/get") public Blog dummyComment() { Blog blog=new Blog();
	 * blog.setId(3L); blog.setTitle("Java"); blog.setContent("Practice Everyday");
	 * blog.setCreatedAt(LocalDateTime.now());
	 * blog.setUpdatedAt(LocalDateTime.now()); blog.setAuthorId(5L);
	 * 
	 * return blog;
	 * 
	 * }
	 */

}
