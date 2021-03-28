package com.peasch.service.Impl;

import com.googlecode.jmapper.JMapper;
import com.peasch.model.dto.Role.RoleDto;
import com.peasch.model.dto.User.UserWithRoleDTO;
import com.peasch.model.dto.WaitList.WaitListWithAllDto;
import com.peasch.model.entities.Role;
import com.peasch.model.entities.User;
import com.peasch.model.entities.WaitListDemand;
import com.peasch.repository.dao.WaitListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class WaitListServiceImpl {
    @Autowired
    private WaitListDao waitListDao;
    @Autowired
    private JMapper<WaitListWithAllDto, WaitListDemand> dtoToWaitListMapper;
    @Autowired
    private JMapper<WaitListDemand, WaitListWithAllDto> wLToDtoMapper;

    List<WaitListWithAllDto> getWaitList(){
        List<WaitListDemand> waitListDemands = waitListDao.findAll();
        return waitListDemands.stream().map(x -> dtoToWaitListMapper.getDestination(x)).collect(Collectors.toList());
    }

    List<WaitListWithAllDto> waitListByBookId(Integer bookId){
        List<WaitListDemand> waitListDemands = waitListDao.findWaitListDemandsByBook_Id(bookId);
        return waitListDemands.stream().map(x -> dtoToWaitListMapper.getDestination(x)).collect(Collectors.toList());
    }
}
