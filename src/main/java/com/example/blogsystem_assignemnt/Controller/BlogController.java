package com.example.blogsystem_assignemnt.Controller;

import com.example.blogsystem_assignemnt.Api.ApiResponse;
import com.example.blogsystem_assignemnt.Model.Blog;
import com.example.blogsystem_assignemnt.Model.User;
import com.example.blogsystem_assignemnt.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;


    @GetMapping("/get")
    public ResponseEntity getAllBlogs(){
        return ResponseEntity.status(200).body(blogService.getBlogs());
    }

    @PostMapping("/add")
    public ResponseEntity addNewBlog(@AuthenticationPrincipal User user , @RequestBody @Valid Blog blog){
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(200).body(new ApiResponse("the blog added successfully"));
    }

    @PutMapping("/update/{blog_id}")
    public ResponseEntity updateBlogController(@AuthenticationPrincipal User user , @PathVariable Integer blog_id , @RequestBody @Valid Blog blog){
        blogService.updateBlog(blog_id , blog);
        return ResponseEntity.status(200).body(new ApiResponse("The blog updated successfully"));
    }
    @DeleteMapping("/delete/{blog_id}")
    public ResponseEntity deleteBlogController(@AuthenticationPrincipal User user , @PathVariable Integer blog_id){
        blogService.deleteBlog(blog_id);
        return ResponseEntity.status(200).body(new ApiResponse("The blog deleted successfully"));
    }

    @GetMapping("/getById/{blog_id}")
    public ResponseEntity getBlogsByIdController(@AuthenticationPrincipal User user , @PathVariable Integer blog_id){
        return ResponseEntity.status(200).body(blogService.getBlogById(blog_id));
    }

    @GetMapping("/getTitle/{title}")
    public ResponseEntity getBlogByTitleController(@AuthenticationPrincipal User user , @PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }
}
