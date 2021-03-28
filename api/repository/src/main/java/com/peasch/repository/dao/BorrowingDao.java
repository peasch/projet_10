package com.peasch.repository.dao;

import com.peasch.model.entities.Borrowing;
import com.peasch.model.entities.Copy;
import com.peasch.model.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface BorrowingDao extends JpaRepository<Borrowing, Integer> {

 Set<Borrowing> findBorrowingByUser_Id(Integer id);
 Set<Borrowing> findBorrowingByUser_IdAndAndReturnedIsFalse(Integer id);
 Set<Borrowing> findBorrowingByUser_IdAndAndReturnedIsTrue(Integer id);
Borrowing findBorrowingByCopy_IdAndReturnedIsFalse(Integer id);
 Set<Borrowing> findBorrowingByReturnedFalse();

}
