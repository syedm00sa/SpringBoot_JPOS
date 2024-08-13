package com.users.service.Impl;

import com.users.persistence.entity.User;
import com.users.persistence.model.TransactionRequest;
import com.users.persistence.repository.UserRepository;
import com.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    RestTemplate restTemplate = new RestTemplate();

    @Value("${bank.service.url}")
    private String bankServiceUrl;

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String initiateTransaction(String userId, String amount) {
        String response = restTemplate.postForObject(bankServiceUrl, new TransactionRequest(userId, amount), String.class);
        return response;
    }
}
