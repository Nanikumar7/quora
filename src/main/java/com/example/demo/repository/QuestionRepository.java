package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {



 @Query("SELECT q FROM Question q WHERE " + 
        "(:body IS NOT NULL AND LOWER(q.body) LIKE LOWER(CONCAT('%',:body,'%'))) OR " +
        "(:title IS NOT NULL AND LOWER(q.title) = LOWER(:title))"
 )   
 List<Question> findByTextOrTag(@Param("body") String body, @Param("title") String title);

}
