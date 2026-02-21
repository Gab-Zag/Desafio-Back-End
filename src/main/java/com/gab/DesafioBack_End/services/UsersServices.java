package com.gab.DesafioBack_End.services;

import com.gab.DesafioBack_End.DTO.RegisterUsersDTO;
import com.gab.DesafioBack_End.entitys.Users;
import com.gab.DesafioBack_End.exceptions.InvalidCPFException;
import com.gab.DesafioBack_End.exceptions.InvalidEmailException;
import com.gab.DesafioBack_End.repositorys.UserRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

import static com.gab.DesafioBack_End.DTO.CPFValidate.isCPF;
import static com.gab.DesafioBack_End.DTO.CPFValidate.sendCPF;

@Service
public class UsersServices {
    private final UserRepository userRepository;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]+$");

    public UsersServices(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void register(RegisterUsersDTO dto){

        validateEmail(dto.email());

        String cpfValidated = validateCPF(dto.cpf());

        Users users = new Users();

        users.setName(dto.name());
        users.setCpf(cpfValidated);
        users.setEmail(dto.email());
        users.setPassword(dto.password());
        users.setType(dto.type());
        users.setAmount(dto.amount());

        userRepository.save(users);
    }

    private void validateEmail(String email){
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()){
            throw new InvalidEmailException("Email Invalido");
        }
    }

    private String validateCPF(String cpf){
        if(cpf == null || !isCPF(cpf)){
            throw new InvalidCPFException("CPF Invalido");
        }else{
            return sendCPF(cpf);
        }
    }

}