package com.tradify_markets.tradify.service;

import com.tradify_markets.tradify.model.UserShare;
import com.tradify_markets.tradify.repository.UserRepository;
import com.tradify_markets.tradify.repository.UserShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserShareService {
    private final UserShareRepository userShareRepository;
    private final UserRepository userRepository;

    public UserShare findByUser(Integer id) {
        return userShareRepository.findByUser(userRepository.findById(id).orElse(null));
    }
}
