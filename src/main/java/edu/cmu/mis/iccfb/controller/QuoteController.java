package edu.cmu.mis.iccfb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.cmu.mis.iccfb.model.Author;
import edu.cmu.mis.iccfb.model.Quote;
import edu.cmu.mis.iccfb.service.AuthorService;
import edu.cmu.mis.iccfb.service.QuoteService;

@RestController
public class QuoteController {
    
    @Autowired
    private QuoteService quoteService;
    
    @Autowired
    private AuthorService authorService;
    
    @RequestMapping("/api/quote/random")
    public Quote random() {
        System.out.println("QC random() is running");
        return quoteService.randomQuote();
    }
    
    @RequestMapping("/api/quote/list")
    public Quote[] list(@RequestBody Author author) {
        //or can try passing quote
        System.out.println("You are looking for quotes from " + author);
        return quoteService.findByAuthor(author);
    }
    
    @RequestMapping(value = "/api/quote", method = RequestMethod.POST)
    public void saveQuote(@RequestBody Quote quote) {
        
        //Not working is just name is there. Should be a json file
//        Author au = new Author("Douglas Adams");
//        authorService.save(au);
//        Quote[] q = quoteService.findByAuthor(au);
//        System.out.println(q);
//        System.out.println("*******************");
//        System.out.println(q[0].getText());
//        System.out.println("*******************");
        
        Author a = authorService.findByName(quote.getAuthor().getName());
        
        if (a == null) {
            System.out.println("----------Saving as a !!!NEW!!! author----------");
            authorService.save(quote.getAuthor());
        } else {
            System.out.println("----------Saving as a !!!OLD!!! author----------");
            quote.setAuthor(a);
        }
        
        System.out.println("Saving quote");
        quoteService.save(quote);
    }
    
    
    public Quote fallback() {
        Quote q = new Quote();
        Author a = new Author("Confucius");
        q.setText("The superior man is modest in his speech, but exceeds in his actions.");
        q.setAuthor(a);
        return q; 
    }
}
