package com.challengepicpay.services;

import com.challengepicpay.domain.user.User;
import com.challengepicpay.domain.user.UserType;
import com.challengepicpay.dto.UserDTO;
import com.challengepicpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuário do tpo Lojista não esta autorizada a realizar transacao");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void save(User user) {
        this.userRepository.save(user);
    }

    public User createUser(UserDTO userDTO) {
        User newUser = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .password(userDTO.getPassword())
                .userType(userDTO.getUserType())
                .mail(userDTO.getEmail())
                .balance(userDTO.getBalance())
                .document(userDTO.getDocument())
                .build();
        this.save(newUser);

        return newUser;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
