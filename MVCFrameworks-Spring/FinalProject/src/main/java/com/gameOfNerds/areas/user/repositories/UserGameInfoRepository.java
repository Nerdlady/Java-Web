package com.gameOfNerds.areas.user.repositories;

import com.gameOfNerds.areas.user.entities.UserGameInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGameInfoRepository extends JpaRepository<UserGameInfo,Long> {
    UserGameInfo findByUserId(Long userId);
    UserGameInfo findByUserUsername(String username);
    List<UserGameInfo> findAllByOrderByScoreDesc();
}
