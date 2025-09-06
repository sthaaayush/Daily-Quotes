package com.learningAayush.dailyQuotes.controller;

import com.learningAayush.dailyQuotes.entity.Quotes;
import com.learningAayush.dailyQuotes.services.QuotesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/dailyQuotes")
public class QuoteController {

    @Autowired
    private QuotesServices quotesServices;

    @GetMapping
    public ResponseEntity<?> getAllQuotes(){
         List<Quotes> collect = quotesServices.getAllQuotes();
         if(!collect.isEmpty()){
             return new ResponseEntity<>(collect, HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> createQuotes(@RequestBody Quotes quotes) {
        try {
            quotes.setDate(LocalDateTime.now());
            quotesServices.saveQuotes(quotes);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException("Entry Issue while creating quotes " +e);
        }
    }

    @GetMapping("/{tags}")
    public ResponseEntity<?> getQuotesByTag(@PathVariable String tags){
        List<Quotes> collect = quotesServices.getQuotesByTags(tags);
        if(!collect.isEmpty()){
            return new ResponseEntity<>(collect, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
