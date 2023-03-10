package com.tradify_markets.tradify.repository;

import com.tradify_markets.tradify.model.NewsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsModel, Integer> {
    List<NewsModel> findByShareId(Integer id);
}
