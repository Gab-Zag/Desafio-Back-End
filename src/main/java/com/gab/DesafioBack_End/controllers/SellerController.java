package com.gab.DesafioBack_End.controllers;

import com.gab.DesafioBack_End.dtos.records.RegisterSellerDTO;
import com.gab.DesafioBack_End.services.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService){
        this.sellerService = sellerService;
    }

    @PostMapping("/register/seller")
    public ResponseEntity<Void> register(@RequestBody RegisterSellerDTO dto){
        sellerService.register(dto);
        return ResponseEntity.ok().build();
    }

}
