package com.users.service;

import com.users.persistence.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    User getUser(String username);

    String initiateTransaction(String userId, String amount);
}
