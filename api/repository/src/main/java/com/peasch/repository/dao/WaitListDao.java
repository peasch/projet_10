package com.peasch.repository.dao;

import com.peasch.model.entities.User;
import com.peasch.model.entities.WaitListDemand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaitListDao extends JpaRepository<WaitListDemand, Integer> {

    List<WaitListDemand> findWaitListDemandsByBook_Id(Integer bookId);
}
