package com.peasch.controller;

import com.peasch.model.dto.Author.AuthorDto;
import com.peasch.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService service;


    @GetMapping
    public List<AuthorDto> getAuthors( @RequestHeader(name = "Authorization") String token){
        return service.getAuthors();
    }

    @GetMapping("{id}")
    public AuthorDto getUserById(@PathVariable(value = "id")Integer id, @RequestHeader(name = "Authorization") String token){
        return service.findById(id);
    }

   /* @PostMapping("add")
    public void addAuthor (@RequestBody AuthorDto authorDto){
        service.save(authorDto);
    }*/
 }
