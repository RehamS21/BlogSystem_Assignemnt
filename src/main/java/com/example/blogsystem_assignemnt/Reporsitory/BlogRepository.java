package com.example.blogsystem_assignemnt.Reporsitory;

import com.example.blogsystem_assignemnt.Model.Blog;
import com.example.blogsystem_assignemnt.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog , Integer> {
    Blog findBlogById(Integer id);

    List<Blog> findAllByUser(User user);

    @Query("select b from Blog b where b.title = ?1 ")
    Blog getBlogByTitle(String title);
}
