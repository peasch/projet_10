package com.peasch.repository.dao;

import com.peasch.model.entities.User;
import com.peasch.model.entities.WaitListDemand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaitListDao extends JpaRepository<WaitListDemand, Integer> {

    List<WaitListDemand> findWaitListDemandsByBookId(Integer bookId);
    WaitListDemand findWaitListDemandByBook_IdAndUser_Id(Integer bookId, Integer userId);
    List<WaitListDemand> findWaitListDemandsByUser_Id(Integer id);
    WaitListDemand findWaitListDemandById(Integer id);
}
