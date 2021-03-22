package com.peasch.repository.dao;

import com.peasch.model.entities.Book;
import com.peasch.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Integer> {
}
