package com.gab.DesafioBack_End.controllers;

import com.gab.DesafioBack_End.dtos.records.RegisterUsersDTO;
import com.gab.DesafioBack_End.dtos.transfer.TransferUserbyUser;
import com.gab.DesafioBack_End.services.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService){
        this.usersService = usersService;
    }

    @PostMapping("/register/users")
    public ResponseEntity<Void> register(@RequestBody RegisterUsersDTO dto){
        usersService.register(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/transfer/{senderid}/{reciverid}")
    public ResponseEntity<Void> tranfer(@PathVariable Integer senderid, @PathVariable Integer reciverid, @RequestBody TransferUserbyUser dto){
        usersService.trasfer(senderid, reciverid,dto);
        return ResponseEntity.ok().build();
    }
}
