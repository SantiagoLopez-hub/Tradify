package com.tradify_markets.tradify.route;

import com.tradify_markets.tradify.model.NewsModel;
import com.tradify_markets.tradify.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class News {
    private final NewsService newsService;

    @GetMapping
    public List<NewsModel> news() {
        return newsService.findAll();
    }

    @GetMapping("/{id}")
    public NewsModel news(@PathVariable Integer id) {
        return newsService.findById(id);
    }

    @GetMapping("/share/{id}")
    public List<NewsModel> newsByShare(@PathVariable Integer id) {
        return newsService.findByShareId(id);
    }

    @PostMapping("/create")
    public NewsModel createNews(@RequestBody NewsModel news) {
        return newsService.save(news);
    }

    @PutMapping("/update/{id}")
    public NewsModel updateNews(@PathVariable Integer id, @RequestBody NewsModel news) {
        return newsService.update(id, news);
    }
}
