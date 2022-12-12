package com.tradify_markets.tradify.repository;

import com.tradify_markets.tradify.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
}
