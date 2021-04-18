package com.peasch.service.Impl;

import com.googlecode.jmapper.JMapper;
import com.peasch.model.dto.Book.BookWithoutCopiesDTO;
import com.peasch.model.dto.User.UserDto;
import com.peasch.model.dto.WaitList.WaitListWithAllDto;
import com.peasch.model.entities.WaitListDemand;
import com.peasch.repository.dao.WaitListDao;
import com.peasch.service.BookService;
import com.peasch.service.BorrowingService;
import com.peasch.service.CopyService;
import com.peasch.service.WaitListService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class WaitListServiceImpl implements WaitListService {
    @Autowired
    private WaitListDao waitListDao;
    @Autowired
    private JMapper<WaitListWithAllDto, WaitListDemand> dtoToWaitListMapper;
    @Autowired
    private JMapper<WaitListDemand, WaitListWithAllDto> wLToDtoMapper;
    @Autowired
    private BookService bookService;
    @Autowired
    private CopyService copyService;
    @Autowired
    private BorrowingService borrowingService;

    public List<WaitListWithAllDto> getWaitList() {
        List<WaitListDemand> waitListDemands = waitListDao.findAll();
        return waitListDemands.stream().map(x -> dtoToWaitListMapper.getDestination(x)).collect(Collectors.toList());
    }

    public List<WaitListWithAllDto> waitListByBookId(Integer bookId) {
        List<WaitListDemand> waitListDemands = waitListDao.findWaitListDemandsByBookId(bookId);
        return waitListDemands.stream().map(x -> dtoToWaitListMapper.getDestination(x)).collect(Collectors.toList());
    }

    public WaitListWithAllDto getWaitListOfUser(Integer bookId, UserDto user) {
        return dtoToWaitListMapper.getDestination(waitListDao.findWaitListDemandByBook_IdAndUser_Id(bookId, user.getId()));
    }

    public List<WaitListWithAllDto> getAllWaitListofuser(UserDto user) {
        List<WaitListDemand> waitListDemands = waitListDao.findWaitListDemandsByUser_Id(user.getId());
        return waitListDemands.stream().map(x -> dtoToWaitListMapper.getDestination(x)).collect(Collectors.toList());
    }

    public Boolean isWaitListed(Integer bookId) {
        List<WaitListWithAllDto> WL = this.waitListByBookId(bookId);
        if (!WL.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean ExistingWaitList(Integer bookId, UserDto user) {
        if (waitListDao.findWaitListDemandByBook_IdAndUser_Id(bookId, user.getId()) != null) {
            return true;
        } else {
            return false;
        }
    }

    public WaitListWithAllDto save(UserDto user, Integer id) throws NotFoundException {
        BookWithoutCopiesDTO book = bookService.findById(id);
        WaitListWithAllDto waitListWithAllDto = new WaitListWithAllDto();
        waitListWithAllDto.setBook(book);
        waitListWithAllDto.setUser(user);
        waitListWithAllDto.setWaitListDate(LocalDate.now());
        waitListWithAllDto.setFirstReturnDate(borrowingService.findBorrowingsByBookId(book.getId()));
        return dtoToWaitListMapper.getDestination(waitListDao.save(wLToDtoMapper.getDestination(waitListWithAllDto)));

    }

    public Boolean isWaitListable(Integer bookId) {
        List<WaitListWithAllDto> waitLists = this.waitListByBookId(bookId);
        int numberOfCopies = copyService.findNumberOfCopies(bookId);
        if (waitLists.size() < (numberOfCopies * 2)) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteWaitlistDemand(Integer id) {
        WaitListDemand waitListDemand = waitListDao.findWaitListDemandById(id);
        waitListDao.delete(waitListDemand);
    }

    public WaitListWithAllDto availableBookofWaitLists(Integer id) {
        LocalDate today = LocalDate.now();
        List<WaitListWithAllDto> waitLists = this.waitListByBookId(id);
        if (waitLists != null) {
            WaitListWithAllDto firstWaitLister = waitLists.get(0);
            if (firstWaitLister.getContactDate() == null) {
                firstWaitLister.setContactDate(today);
                this.update(firstWaitLister);
            } else if (firstWaitLister.getContactDate().plusDays(2).compareTo(today) < 0) {
                this.deleteWaitlistDemand(firstWaitLister.getId());
                waitLists = this.waitListByBookId(id);
                firstWaitLister = waitLists.get(0);
                firstWaitLister.setContactDate(today);
                this.update(firstWaitLister);
            }
            return firstWaitLister;
        } else {
            return null;
        }

    }
    public WaitListWithAllDto update(WaitListWithAllDto waitListWithAllDto) {

        return dtoToWaitListMapper.getDestination(waitListDao.save(wLToDtoMapper.getDestination(waitListWithAllDto)));
    }



}
