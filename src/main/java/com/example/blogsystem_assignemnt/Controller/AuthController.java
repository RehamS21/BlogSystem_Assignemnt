package com.example.blogsystem_assignemnt.Controller;

import com.example.blogsystem_assignemnt.Api.ApiResponse;
import com.example.blogsystem_assignemnt.Model.User;
import com.example.blogsystem_assignemnt.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid User user){
        authService.register(user);
        return ResponseEntity.status(200).body(new ApiResponse("The user registered successfully"));
    }

    @GetMapping("/logout")
    public ResponseEntity logOut(){
        return ResponseEntity.status(200).body(new ApiResponse("You log out successfully"));
    }
}
