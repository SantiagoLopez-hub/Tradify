package com.tradify_markets.tradify.repository;

import com.tradify_markets.tradify.model.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareRepository extends JpaRepository<Share, Integer> {}
