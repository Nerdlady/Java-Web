package com.gameOfNerds.areas.question.repositories;

import com.gameOfNerds.areas.question.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findAllByIsApproveOrderByIdDesc(Boolean isApprove);
}
