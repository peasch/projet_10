package com.peasch.service.Impl;

import com.googlecode.jmapper.JMapper;
import com.peasch.model.dto.Author.AuthorDto;
import com.peasch.model.entities.Author;
import com.peasch.model.entities.Book;
import com.peasch.repository.dao.AuthorDao;
import com.peasch.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorDao authorDao;

    @Autowired
    private JMapper<AuthorDto, Author>  authorToDTOMapper;
    @Autowired
    private JMapper<Author, AuthorDto>  dtoToAuthorMapper;

    public List<AuthorDto> getAuthors(){
        List<Author> authors = authorDao.findAll();
        return authors.stream().map(x->authorToDTOMapper.getDestination(x)).collect(Collectors.toList());
    }


    public AuthorDto findById(Integer id){
        return authorToDTOMapper.getDestination(authorDao.findById(id).get());

    }

    /*public AuthorDto save(AuthorDto authorDto){
        return mapper.fromAuthorToDto(authorDao.save(mapper.fromDtoToAuthor(authorDto)));
    }

    public AuthorDto findAuthorByName (String name){
        AuthorDto author = mapper.fromAuthorToDto(authorDao.findAuthorByNameLike(name));
        return author;
    }*/
}



