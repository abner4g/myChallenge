package com.challengepicpay.services;

import com.challengepicpay.domain.transaction.Transaction;
import com.challengepicpay.domain.user.User;
import com.challengepicpay.dto.TransactionDTO;
import com.challengepicpay.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
        User sender = this.userService.findUserById(transactionDTO.senderId());
        User receiver = this.userService.findUserById(transactionDTO.receiverId());


        boolean isAuthorized = this.authorizeTemplate(sender, transactionDTO.value());
        if (!isAuthorized){
            throw new Exception("requisição não autorizada");
        }

        Transaction transaction = Transaction.builder()
                .amount(transactionDTO.value())
                .receiver(receiver)
                .sender(sender)
                .timestamp(LocalDateTime.now())
                .build();

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        repository.save(transaction);
        userService.save(sender);
        userService.save(receiver);

        return transaction;
    }

    public boolean authorizeTemplate(User sender, BigDecimal value) {

//        var responseTemplate =
//                restTemplate.getForEntity("https://www.google.com.br/?gws_rd=ssl", Map.class);
//
//        return responseTemplate.getStatusCode() == HttpStatus.OK;

        return  true;

    }


}
