package com.learningAayush.dailyQuotes.controller;

import com.learningAayush.dailyQuotes.entity.Quotes;
import com.learningAayush.dailyQuotes.services.QuotesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/id/{myId}")
    public ResponseEntity<Quotes> getQuotesById(@PathVariable Long myId){
        Optional<Quotes> collect = quotesServices.getQuotesById(myId);
        if(collect.isPresent()){
            return new ResponseEntity<>(collect.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createQuotes(@RequestBody Quotes quotes) {
        try {
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

    @PutMapping("/id/{myId}")
    public ResponseEntity<?> updateQuotesById(@PathVariable Long myId, @RequestBody Quotes quotes){
        Optional<Quotes> collect = quotesServices.getQuotesById(myId);
        if(collect.isPresent()){
            Quotes oldQuote = collect.get();
            oldQuote.setQuote(!quotes.getQuote().isEmpty() && quotes.getQuote() != null ? quotes.getQuote() : oldQuote.getQuote());
            oldQuote.setTags(!quotes.getTags().isEmpty() && quotes.getTags() != null ? quotes.getTags() : oldQuote.getTags());
            oldQuote.setId(myId);
            quotesServices.saveQuotes(oldQuote);
            return new ResponseEntity<>(oldQuote, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
