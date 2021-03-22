package com.peasch.repository.dao;

import com.peasch.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Integer>{
    List<Book> findBooksByAuthor_NameLike(String authorName);
    List<Book> findBooksByTitleLike(String title);
    List<Book> findBooksByTitleLikeAndAuthor_NameLikeAndCategory_NameLike(String title,String name,String category);

    @Query(value = "SELECT  b.fk_author FROM Book b where b.id = :id",nativeQuery = true)
    Integer findAuthorOfBookById(Integer id);
    @Query(value = "SELECT  b.fk_category FROM Book b where b.id = :id",nativeQuery = true)
    Integer findCategoryOfBookById(Integer id);
}
