package com.gab.DesafioBack_End.controllers;

import com.gab.DesafioBack_End.DTO.RegisterUsersDTO;
import com.gab.DesafioBack_End.services.UsersServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class UsersControllers {

    private final UsersServices usersServices;

    public UsersControllers(UsersServices usersServices){
        this.usersServices = usersServices;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterUsersDTO dto){
        usersServices.register(dto);
        return ResponseEntity.ok().build();
    }
}
