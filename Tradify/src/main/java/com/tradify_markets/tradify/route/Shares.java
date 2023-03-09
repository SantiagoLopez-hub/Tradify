package com.tradify_markets.tradify.route;

import com.tradify_markets.tradify.model.Share;
import com.tradify_markets.tradify.repository.ShareRepository;
import com.tradify_markets.tradify.service.ShareService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shares")
public class Shares {
    private final ShareService shareService;

    public Shares(ShareService shareService) {
        this.shareService = shareService;
    }

    @GetMapping
    public List<Share> shares() {
        return shareService.findAll();
    }

    @GetMapping("/{id}")
    public Share getShare(@PathVariable Integer id) {
        return shareService.findById(id);
    }

    @PutMapping("/{id}")
    public Share updateShare(@PathVariable Integer id, @RequestBody Share share) {
        return shareService.updateShare(id, share);
    }

    @DeleteMapping("/{id}")
    public void deleteShare(@PathVariable Integer id) {
        shareService.deleteById(id);
    }
}
