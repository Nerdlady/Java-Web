package com.gameOfNerds.areas.clan.repositories;

import com.gameOfNerds.areas.clan.entities.Clan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClanRepository extends JpaRepository<Clan,Long> {
    List<Clan> findAllByOrderByTotalScoreDesc();
    Clan findByOwnerId(Long id);
}
