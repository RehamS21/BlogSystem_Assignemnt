package com.example.blogsystem_assignemnt.Service;

import com.example.blogsystem_assignemnt.Api.ApiException;
import com.example.blogsystem_assignemnt.Model.Blog;
import com.example.blogsystem_assignemnt.Model.User;
import com.example.blogsystem_assignemnt.Reporsitory.AuthRepository;
import com.example.blogsystem_assignemnt.Reporsitory.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    ////////////////////  normal CRUD  ///////////////////////
    public List<Blog> getBlogs(){
        return blogRepository.findAll();
    }

    public void addBlog(Integer user_id , Blog blog){
       User user = authRepository.findUserById(user_id);

       if (user == null)
           throw new ApiException("Sorry, the user id is wrong");

       blog.setUser(user);

       blogRepository.save(blog);
    }

    public void updateBlog(Integer blog_id , Blog blog){
        Blog oldBlog = blogRepository.findBlogById(blog_id);

        if (oldBlog == null)
            throw new ApiException("Sorry the blog id is wrong");

        oldBlog.setTitle(blog.getTitle());
        oldBlog.setBody(blog.getBody());

        blogRepository.save(oldBlog);
    }

    public void deleteBlog(Integer blog_id){
        Blog deleteBlog = blogRepository.findBlogById(blog_id);

        if (deleteBlog == null)
            throw new ApiException("Sorry the blog id is wrong");

        blogRepository.delete(deleteBlog);

    }
    /////////////////////// With security ////////////////////////
    public List<Blog> getBlogUser(Integer user_id){
        User user = authRepository.findUserById(user_id);
        return blogRepository.findAllByUser(user);
    }

    public Blog getBlogById(Integer blog_id){
        Blog blog = blogRepository.findBlogById(blog_id);

        if (blog == null)
            throw new ApiException("No blog found with this id");

        return blog;
    }

    public Blog getBlogByTitle(String title){
        Blog blog = blogRepository.findBlogByTitle(title);

        if (blog == null)
            throw new ApiException("Sorry, can't see this blog");

        return blog;
    }

}
