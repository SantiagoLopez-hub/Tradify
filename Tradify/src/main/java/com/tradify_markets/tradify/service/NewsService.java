package com.tradify_markets.tradify.service;

import com.tradify_markets.tradify.model.NewsModel;
import com.tradify_markets.tradify.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NewsService {
    private final NewsRepository newsRepository;

    public List<NewsModel> findAll() {
        return newsRepository.findAll();
    }

    public NewsModel findById(Integer id) {
        return newsRepository.findById(id).orElse(null);
    }

    public List<NewsModel> findByShareId(Integer id) {
        return newsRepository.findByShareId(id);
    }

    public NewsModel save(NewsModel news) {
        return newsRepository.save(news);
    }

    public NewsModel update(Integer id, NewsModel news) {
        news.setId(id);
        return newsRepository.save(news);
    }

    public void deleteById(Integer id) {
        newsRepository.deleteById(id);
    }
}
