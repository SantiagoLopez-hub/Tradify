package com.tradify_markets.tradify.repository;

import com.tradify_markets.tradify.model.User;
import com.tradify_markets.tradify.model.UserShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserShareRepository extends JpaRepository<UserShare, Integer> {
    UserShare findByUser(User user);
}
