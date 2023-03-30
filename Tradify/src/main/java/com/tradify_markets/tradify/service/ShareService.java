package com.tradify_markets.tradify.service;

import com.tradify_markets.tradify.model.Share;
import com.tradify_markets.tradify.repository.ShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ShareService {
    private final ShareRepository shareRepository;

    public List<Share> findAll() {
        return shareRepository.findAll();
    }

    public Share findById(@PathVariable Integer id) {
        return shareRepository.findById(id).orElse(null);
    }

    public Share updateShare(@PathVariable Integer id, @RequestBody Share share) {
        share.setId(id);
        return shareRepository.save(share);
    }

    public void deleteById(@PathVariable Integer id) {
        shareRepository.deleteById(id);
    }
}
