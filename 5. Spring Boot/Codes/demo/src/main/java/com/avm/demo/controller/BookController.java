package com.avm.demo.controller;

import com.avm.demo.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")  //It is Base URL
public class BookController {

    private Map<Long, Book > bookDB= new HashMap<>();

    //Every time when API response us something it is very bad practice to return String
    //Instead of retun String we Wrap that Response in ResponseEntity

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        //RespinseEntity-> It is Noting but a Class in which our API response will be Wrap
        return ResponseEntity.ok(new ArrayList<>(bookDB.values()));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        bookDB.put(book.getId(), book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookbyId(@PathVariable Long id){
        Book book=bookDB.get(id);
        if (book == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(book);
    }
    //When ever you use Path Variable at that time the name of GetMapping{/id} should be same with @PathVariable id

    // put: Updatebook fully -> it will change complete book Object
    //patch : Update only partially -> it will just change some part of obj not completet things of OBJ
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable Long id, @RequestBody Book book){
        Book existing=bookDB.get(id);
        if (existing == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        bookDB.put(id, book);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 200ok
    }

    @PatchMapping("/{id}/price")
    public ResponseEntity<Book> updatePrice(@PathVariable Long id, @RequestBody Double newPrice){
        Book existing=bookDB.get(id);
        if (existing== null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        existing.setPrice(newPrice);
        bookDB.put(id, existing);
        return ResponseEntity.ok(existing); // 200ok
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Book> Delete(@PathVariable Long id){
        Book existing=bookDB.remove(id);
        if (existing== null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }
}