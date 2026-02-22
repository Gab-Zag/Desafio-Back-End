package com.gab.DesafioBack_End.services;

import com.gab.DesafioBack_End.dtos.login.LoginUsersAndSellersDTO;
import com.gab.DesafioBack_End.dtos.registers.RegisterUsersDTO;
import com.gab.DesafioBack_End.dtos.transfer.TransferUserBySeller;
import com.gab.DesafioBack_End.dtos.transfer.TransferUserByUser;
import com.gab.DesafioBack_End.entities.Seller;
import com.gab.DesafioBack_End.entities.Transfer;
import com.gab.DesafioBack_End.entities.Users;
import com.gab.DesafioBack_End.exceptions.*;
import com.gab.DesafioBack_End.repositorys.SellerRepository;
import com.gab.DesafioBack_End.repositorys.TransferRepository;
import com.gab.DesafioBack_End.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

import static com.gab.DesafioBack_End.validations.CPFValidate.isCPF;
import static com.gab.DesafioBack_End.validations.CPFValidate.sendCPF;

@Service
public class UsersService {
    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final TransferRepository transferRepository;
    @Autowired
    private final AuthorizeService authorizeService;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]+$");

    public UsersService(UserRepository userRepository,SellerRepository sellerRepository, AuthorizeService authorizeService,TransferRepository transferRepository){
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
        this.authorizeService = authorizeService;
        this.transferRepository = transferRepository;
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

    public void transferByUser(Integer sendeid, Integer reciverid, TransferUserByUser dto){
        Users sender = userRepository.findById(sendeid).orElseThrow(() -> new InvalidIDSenderException("Usuario nao encontrado"));
        Users reciver = userRepository.findById(reciverid).orElseThrow(() -> new InvalidIDReciverException("Destinario Inexistente"));

        if (sender.getAmount().compareTo(dto.value()) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }else if(!authorizeService.isAuthorized()){
            throw new RuntimeException("Transferência negada");
        } else{
            sender.setAmount(sender.getAmount().subtract(dto.value()));
            reciver.setAmount(reciver.getAmount().add(dto.value()));
            Transfer transfer = new Transfer();
            transfer.setSender_user_id(sender.getId());
            transfer.setReceiver_user_Id(reciverid);
            transfer.setValue(dto.value());
            userRepository.save(sender);
            userRepository.save(reciver);
            transferRepository.save(transfer);
        }


    }

    @Transactional
    public void transferBySeller(Integer sendeid, Integer reciverid, TransferUserBySeller dto){
        Users sender = userRepository.findById(sendeid).orElseThrow(() -> new InvalidIDSenderException("Usuario nao encontrado"));
        Seller reciver = sellerRepository.findById(reciverid).orElseThrow(() -> new InvalidIDReciverException("Destinario Inexistente"));

        if (sender.getAmount().compareTo(dto.value()) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }else if(!authorizeService.isAuthorized()){
            throw new RuntimeException("Transferência negada");
        }else{
            sender.setAmount(sender.getAmount().subtract(dto.value()));
            reciver.setAmount(reciver.getAmount().add(dto.value()));
            Transfer transfer = new Transfer();
            transfer.setSender_user_id(sendeid);
            transfer.setReceiver_seller_id(reciverid);
            transfer.setValue(dto.value());
            userRepository.save(sender);
            sellerRepository.save(reciver);
            transferRepository.save(transfer);
        }
    }

    public String login(LoginUsersAndSellersDTO dto){
        Users users = userRepository.findByEmail(dto.email()).orElseThrow(() -> new InvalidEmailException("Nenhum usuário possui este Email"));

        if (!dto.password().equals(users.getPassword())){
            throw new InvalidPassowordException("Senha incorreta");
        }

        return "Login realizado com sucesso";
    }

    private void validateEmail(String email){
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()){
            throw new InvalidEmailException("Email Invalido");
        }
        if(sellerRepository.findByEmail(email).isPresent()){
            throw new InvalidPassowordException("Email ja esta cadastrado");
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