package com.peasch.service;
import com.peasch.model.dto.User.UserDto;
import com.peasch.model.dto.WaitList.WaitListWithAllDto;
import javassist.NotFoundException;

import java.util.List;


public interface WaitListService {


    List<WaitListWithAllDto> getWaitList();
    List<WaitListWithAllDto> waitListByBookId(Integer bookId);
    WaitListWithAllDto save(UserDto user, Integer id) throws NotFoundException;
    Boolean isWaitListable(Integer bookId);
    void deleteWaitlistDemand (Integer waitListId) throws NotFoundException;
    Boolean ExistingWaitList (Integer bookId, UserDto user);
    WaitListWithAllDto getWaitListOfUser(Integer bookId, UserDto user);
    List<WaitListWithAllDto> getAllWaitListofuser(UserDto user);
    Boolean isWaitListed(Integer bookId);
    WaitListWithAllDto availableBookofWaitLists(Integer id);
}
