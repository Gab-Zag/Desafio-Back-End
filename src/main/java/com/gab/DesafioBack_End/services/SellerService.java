package com.gab.DesafioBack_End.services;

import com.gab.DesafioBack_End.dtos.records.RegisterSellerDTO;
import com.gab.DesafioBack_End.entities.Seller;
import com.gab.DesafioBack_End.exceptions.InvalidCNPJException;
import com.gab.DesafioBack_End.exceptions.InvalidEmailException;
import com.gab.DesafioBack_End.repositorys.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

import static com.gab.DesafioBack_End.validations.CNPJValidate.isCNPJ;
import static com.gab.DesafioBack_End.validations.CNPJValidate.sendCNPJ;

@Service
public class SellerService {
    private SellerRepository sellerRepository;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]+$");

    public SellerService(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }

    public void register(RegisterSellerDTO dto){

        validateEmail(dto.email());

        String cnpjValidated = validateCNPJ(dto.cnpj());

        Seller seller = new Seller();

        seller.setName(dto.name());
        seller.setCnpj(cnpjValidated);
        seller.setEmail(dto.email());
        seller.setPassword(dto.password());
        seller.setAmount(dto.amount());

        sellerRepository.save(seller);
    }

    private void validateEmail(String email){
        if(email == null || !EMAIL_PATTERN.matcher(email).matches()){
            throw new InvalidEmailException("Email Invalido");
        }
    }

    private String validateCNPJ(String cnpj){
        if((cnpj == null) || !isCNPJ(cnpj)){
            throw new InvalidCNPJException("CNPJ Invalido");
        }else{
            return sendCNPJ(cnpj);
        }
    }
}
