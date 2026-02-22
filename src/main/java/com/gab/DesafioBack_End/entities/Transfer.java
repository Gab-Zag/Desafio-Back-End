package com.gab.DesafioBack_End.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name="transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "sender_user_id", nullable = false)
    private Integer sender_user_id;

    @JoinColumn(name = "sender_user_id", nullable = false)
    private Integer receiver_user_Id;

    @JoinColumn(name = "receiver_seller_id")
    private Integer receiver_seller_id;

    @JoinColumn(name = "receiver_seller_id")
    private BigDecimal value;

    public Transfer(Integer sender_user_id, Integer receiver_user_Id, Integer receiver_seller_id){
        this.sender_user_id = sender_user_id;
        this.receiver_user_Id = receiver_user_Id;
        this.receiver_seller_id = receiver_seller_id;
    }

    public Transfer(){}

    public void setSender_user_id(Integer senderUserId){
        this.sender_user_id = senderUserId;
    }

    public void setReceiver_user_Id(Integer receiver_user_Id){
        this.receiver_user_Id = receiver_user_Id;
    }

    public void setReceiver_seller_id(Integer receiverSellerId){
        this.receiver_seller_id = receiverSellerId;
    }

    public void setValue(BigDecimal value){
        this.value = value;
    }
}
