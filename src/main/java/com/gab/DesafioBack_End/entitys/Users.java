package com.gab.DesafioBack_End.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;


@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(unique = true)
    private String cpf;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String type;

    private BigDecimal amount;

    public Users(String name, String cpf, String email, String password, String type, BigDecimal amount){
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.type = type;
        this.amount = amount;
    }

    public Users(){}

    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getCpf(){
        return cpf;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getType(){
        return type;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }
}
