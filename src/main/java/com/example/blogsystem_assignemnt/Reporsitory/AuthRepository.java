package com.example.blogsystem_assignemnt.Reporsitory;

import com.example.blogsystem_assignemnt.Model.Blog;
import com.example.blogsystem_assignemnt.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User , Integer> {

    User findUserById(Integer id);
    User findUserByUsername(String username);

}
