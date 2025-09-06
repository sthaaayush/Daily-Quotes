package com.learningAayush.dailyQuotes.repository;

import com.learningAayush.dailyQuotes.entity.Quotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuotesRepository extends JpaRepository<Quotes, Long> {
    List<Quotes> findByTagsContaining(String tags);
}
