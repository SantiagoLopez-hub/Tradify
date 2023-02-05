package com.tradify_markets.tradify.service;

import com.tradify_markets.tradify.model.UserShare;
import com.tradify_markets.tradify.repository.UserShareRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserShareService {
    private final UserShareRepository userShareRepository;
    private final UserService userService;

    public UserShareService(UserShareRepository userShareRepository, @Lazy UserService userService) {
        this.userShareRepository = userShareRepository;
        this.userService = userService;
    }

    public UserShare findByUser(Integer id) {
        return userShareRepository.findByUser(userService.findById(id));
    }
}
