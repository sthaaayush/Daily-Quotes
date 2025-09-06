package com.learningAayush.dailyQuotes.services;

import com.learningAayush.dailyQuotes.entity.Quotes;
import com.learningAayush.dailyQuotes.repository.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuotesServices {
    @Autowired
    private QuotesRepository quotesRepository;

    public void saveQuotes(Quotes quote){
        quotesRepository.save(quote);
    }

    public List<Quotes> getAllQuotes(){
        return quotesRepository.findAll();
    }

    public List<Quotes> getQuotesByTags(String tags){
        return quotesRepository.findByTagsContaining(tags);
    }
}
