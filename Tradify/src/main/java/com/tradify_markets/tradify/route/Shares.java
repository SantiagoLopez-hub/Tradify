package com.tradify_markets.tradify.route;

import com.tradify_markets.tradify.model.Share;
import com.tradify_markets.tradify.repository.ShareRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shares")
public class Shares {
    private final ShareRepository shareRepository;

    public Shares(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }

    @GetMapping
    public List<Share> share() {
        return shareRepository.findAll();
    }

    @GetMapping("/{id}")
    public Share share(@PathVariable Integer id) {
        return shareRepository.findById(id).get();
    }

    @PutMapping("/{id}")
    public Share updateShare(@PathVariable Integer id, @RequestBody Share share) {
        share.setId(id);
        return shareRepository.save(share);
    }

    @DeleteMapping("/{id}")
    public void deleteShare(@PathVariable Integer id) {
        shareRepository.deleteById(id);
    }
}
