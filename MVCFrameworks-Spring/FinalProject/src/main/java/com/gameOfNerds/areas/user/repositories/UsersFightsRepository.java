package com.gameOfNerds.areas.user.repositories;

import com.gameOfNerds.areas.user.entities.UsersFight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersFightsRepository extends JpaRepository<UsersFight,Long> {

    @Query("SELECT f FROM UsersFight AS f " +
            "WHERE f.challenger.id = ?1 " +
            "AND f.challenged.id =  ?2 " +
            "AND (f.isChallengerUserAnswered = false" +
            " OR  f.isChallengedUserAnswered = false) ")
    UsersFight findByChallengerIdAndChallengedIdAndNotFinished(Long challengerId, Long challengedId);

    @Query("SELECT f FROM UsersFight AS f " +
            "WHERE f.challenged.id = ?1 " +
            "AND f.challenger.id =  ?2 " +
            "AND (f.isChallengerUserAnswered = false" +
            " OR  f.isChallengedUserAnswered = false) ")
    UsersFight findByChallengedIdAndChallengerIdAndNotFinished(Long challengedId, Long challengerId);

    @Query("SELECT f FROM UsersFight AS f " +
            "WHERE (f.challenger.id = ?1" +
            " OR f.challenger.id =  ?2) " +
            "AND (f.challenged.id =  ?1" +
            " OR f.challenged.id =  ?2) " +
            "AND (f.isChallengerUserAnswered = false" +
            " OR  f.isChallengedUserAnswered = false) ")
    UsersFight getNotFinishedFight(Long currentUserId, Long otherUserId);


    @Query("SELECT COUNT(c) FROM UsersFight AS c WHERE c.challenged.id = :id AND c.isChallengedUserAnswered = false")
    int getChallengedChallengesCount(@Param("id") Long userId);

    @Query("SELECT COUNT(c) FROM UsersFight AS c WHERE c.challenger.id = :id AND c.isChallengerUserAnswered = false")
    int getChallengerChallengesCount(@Param("id") Long userId);

    List<UsersFight> findByChallengedUsernameOrderByIsChallengedUserAnsweredAsc(String username);

    List<UsersFight> findByChallengerUsernameOrderByIsChallengerUserAnsweredAsc(String username);
}
