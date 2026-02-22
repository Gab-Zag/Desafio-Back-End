package com.gab.DesafioBack_End.services;

import com.gab.DesafioBack_End.dtos.login.LoginUsersAndSellersDTO;
import com.gab.DesafioBack_End.dtos.registers.RegisterSellerDTO;
import com.gab.DesafioBack_End.entities.Seller;
import com.gab.DesafioBack_End.exceptions.InvalidCNPJException;
import com.gab.DesafioBack_End.exceptions.InvalidEmailException;
import com.gab.DesafioBack_End.exceptions.InvalidPassowordException;
import com.gab.DesafioBack_End.repositorys.SellerRepository;
import com.gab.DesafioBack_End.repositorys.UserRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

import static com.gab.DesafioBack_End.validations.CNPJValidate.isCNPJ;
import static com.gab.DesafioBack_End.validations.CNPJValidate.sendCNPJ;

@Service
public class SellerService {
    private final UserRepository userRepository;
    private SellerRepository sellerRepository;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]+$");

    public SellerService(SellerRepository sellerRepository, UserRepository userRepository){
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
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

    public String login(LoginUsersAndSellersDTO dto){

        Seller seller = sellerRepository.findByEmail(dto.email()).orElseThrow(() -> new InvalidEmailException("Nenhum usuário possui este Email"));

        if(!seller.getPassword().equals(dto.password())){
            throw new InvalidPassowordException("Senha Invalida");
        }

        return "Login Realizado com sucesso";
    }

    private void validateEmail(String email){
        if(email == null || !EMAIL_PATTERN.matcher(email).matches()){
            throw new InvalidEmailException("Email Invalido");
        }else if(userRepository.findByEmail(email).isPresent()){
            throw new InvalidEmailException("Email ja esta sendo utilizaco");
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
