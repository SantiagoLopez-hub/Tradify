package com.tradify_markets.tradify.repository;

import com.tradify_markets.tradify.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {}
