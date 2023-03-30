package com.tradify_markets.tradify.service;

import com.tradify_markets.tradify.model.Share;
import com.tradify_markets.tradify.model.User;
import com.tradify_markets.tradify.model.UserShare;
import com.tradify_markets.tradify.repository.ShareRepository;
import com.tradify_markets.tradify.repository.UserRepository;
import com.tradify_markets.tradify.repository.UserShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserShareService {
    private final UserShareRepository userShareRepository;
    private final UserRepository userRepository;
    private final ShareRepository shareRepository;

    public List<UserShare> findByUser(Integer id) {
        return userShareRepository.findAllByUser(userRepository.findById(id).orElse(null));
    }

    public List<UserShare> findByUsername(String username) {
        return userShareRepository.findAllByUser(userRepository.findByUsername(username));
    }

    public UserShare findByUserAndShare(String username, Integer shareId) {
        User user = userRepository.findByUsername(username);
        Share share = shareRepository.findById(shareId).orElse(null);

        return userShareRepository.findByUserAndShare(user, share);
    }

    public void save(UserShare userShare) {
        userShareRepository.save(userShare);
    }
}
