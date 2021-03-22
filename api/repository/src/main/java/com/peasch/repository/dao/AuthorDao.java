package com.peasch.repository.dao;

import com.peasch.model.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<Author,Integer> {

    Author findAuthorByNameLike(String name);
}
