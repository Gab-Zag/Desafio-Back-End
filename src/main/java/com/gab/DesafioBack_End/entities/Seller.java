package com.gab.DesafioBack_End.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

@Entity
@Table(name = "seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(unique = true)
    private String cnpj;

    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    private BigDecimal amount;

    public Seller(String name, String cnpj, String email, String password, BigDecimal amount){
        this.name = name;
        this.cnpj = cnpj;
        this.email = email;
        this.password = password;
        this.amount = amount;
    }

    public Seller(){}

    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getCnpj(){
        return cnpj;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }
}
