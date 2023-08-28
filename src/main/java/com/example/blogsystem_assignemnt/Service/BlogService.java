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


//      Any one can see the blogs
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

    public void updateBlog(Integer user_id,Integer blog_id , Blog blog){
        if (blog.getUser().getId() != user_id)
            throw new ApiException("Sorry, you can't update this blog");

        Blog oldBlog = blogRepository.findBlogById(blog_id);
        if (oldBlog == null)
            throw new ApiException("Sorry the blog id is wrong");

        oldBlog.setTitle(blog.getTitle());
        oldBlog.setBody(blog.getBody());

        blogRepository.save(oldBlog);
    }

    public void deleteBlog(Integer user_id,Integer blog_id){

        Blog deleteBlog = blogRepository.findBlogById(blog_id);

        if (deleteBlog == null)
            throw new ApiException("Sorry the blog id is wrong");
        else if (deleteBlog.getUser().getId() != user_id) {
            throw new ApiException("Sorry, you can't delete this blog");
        }

        blogRepository.delete(deleteBlog);

    }


    public Blog getBlogById(Integer user_id, Integer blog_id){
        Blog blog = blogRepository.findBlogById(blog_id);

        if (blog == null)
            throw new ApiException("No blog found with this id");
        else if (blog.getUser().getId() != user_id) {
            throw new ApiException("Sorry, you can't see this blog");
        }
        return blog;
    }

    public Blog getBlogByTitle(Integer user_id,String title){
        Blog blog = blogRepository.getBlogByTitle(title);

        if (blog == null)
            throw new ApiException("Sorry, can't see this blog");
        else if (blog.getUser().getId() != user_id)
            throw new ApiException("Sorry, you can't see this blog");
        return blog;
    }

}
