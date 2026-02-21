package com.gab.DesafioBack_End.services;

import com.gab.DesafioBack_End.dtos.records.RegisterUsersDTO;
import com.gab.DesafioBack_End.dtos.transfer.TransferUserbyUser;
import com.gab.DesafioBack_End.entities.Users;
import com.gab.DesafioBack_End.exceptions.InvalidCPFException;
import com.gab.DesafioBack_End.exceptions.InvalidEmailException;
import com.gab.DesafioBack_End.exceptions.InvalidIDReciverException;
import com.gab.DesafioBack_End.exceptions.InvalidIDSenderException;
import com.gab.DesafioBack_End.repositorys.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

import static com.gab.DesafioBack_End.validations.CPFValidate.isCPF;
import static com.gab.DesafioBack_End.validations.CPFValidate.sendCPF;

@Service
public class UsersService {
    private final UserRepository userRepository;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]+$");

    public UsersService(UserRepository userRepository){
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
        users.setAmount(dto.amount());

        userRepository.save(users);
    }

    public void trasfer(Integer sendeid, Integer reciverid,TransferUserbyUser dto){
        findUsers(reciverid, sendeid);
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

    protected void findUsers(Integer id_reciver, Integer id_sender){

        Optional<Users> optionalUsersReciver =  userRepository.findById(id_reciver);
        Optional<Users> optionalUsersSender = userRepository.findById(id_sender);

        if(optionalUsersReciver.isEmpty()){
            throw new InvalidIDReciverException("Destinario Inexistente");
        }
        if(optionalUsersSender.isEmpty()){
            throw new InvalidIDSenderException("Usuario nao encontrado");
        }
    }

}