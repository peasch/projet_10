package com.peasch.service.Impl;

import com.googlecode.jmapper.JMapper;
import com.peasch.model.dto.Borrowings.BorrowingDto;
import com.peasch.model.dto.Borrowings.BorrowingLateDTO;
import com.peasch.model.dto.Borrowings.BorrowingWithAllDTO;
import com.peasch.model.dto.User.UserDto;
import com.peasch.model.dto.copies.CopyDto;
import com.peasch.model.dto.copies.CopyWithALLDTO;
import com.peasch.model.entities.Borrowing;
import com.peasch.model.entities.Copy;
import com.peasch.repository.dao.BorrowingDao;
import com.peasch.service.BorrowingService;
import com.peasch.service.CopyService;
import com.peasch.service.WaitListService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BorrowingServiceImpl implements BorrowingService {
    @Autowired
    private BorrowingDao borrowingDao;
    @Autowired
    JMapper<BorrowingDto, Borrowing> borrowingToDTOMapper;
    @Autowired
    private JMapper<BorrowingWithAllDTO, Borrowing> borrowingWithAllToDTOMapper;

    @Autowired
    private JMapper<BorrowingLateDTO, Borrowing> borrowingLateToDTOMapper;

    @Autowired
    private JMapper<Borrowing, BorrowingWithAllDTO> dtoToBorrowingWithAllMapper;

    @Autowired
    private JMapper<CopyWithALLDTO, Copy> copyWithAllToDTOMapper;

    @Autowired
    private CopyService copyService;

    @Autowired
    private WaitListService waitListService;


    public List<BorrowingDto> getBorrowings() {
        List<Borrowing> borrowings = borrowingDao.findAll();
        return borrowings.stream().map(x -> borrowingToDTOMapper.getDestination(x)).collect(Collectors.toList());
    }


    public BorrowingDto findById(Integer id) {
        return borrowingToDTOMapper.getDestination(borrowingDao.findById(id).get());


    }

    public BorrowingWithAllDTO findByIdWithAll(Integer id) throws NotFoundException {
        Borrowing borrow = borrowingDao.findById(id).get();
        BorrowingWithAllDTO borrowingDto = borrowingWithAllToDTOMapper.getDestination(borrow);
        borrowingDto.setCopy(copyService.findByCopyWithAll(borrow.getCopy()));
        LocalDate date = borrow.getReturnDate();
        this.isBorrowingLate(id);
        LocalDate today = LocalDate.now();
        Period period = Period.between(today, date);
        int fromMonths = period.getMonths();
        borrowingDto.setDaysToGo(fromMonths * 30 + period.getDays());
        return borrowingDto;

    }


    public BorrowingWithAllDTO save(BorrowingWithAllDTO borrowingWithAllDTO) {

        return borrowingWithAllToDTOMapper.getDestination(borrowingDao.save(dtoToBorrowingWithAllMapper.getDestination(borrowingWithAllDTO)));
    }


    public Set<BorrowingWithAllDTO> findReturnedBorrowingsByUserId(Integer id) throws NotFoundException {
        Set<BorrowingWithAllDTO> returnedBorrowDtos = new HashSet<>();
        Set<Borrowing> borrowings = borrowingDao.findBorrowingByUser_IdAndAndReturnedIsTrue(id);
        for (Borrowing borrow : borrowings) {
            returnedBorrowDtos.add(this.findByIdWithAll(borrow.getId()));
        }
        return returnedBorrowDtos;
    }

    public Set<BorrowingWithAllDTO> findUnReturnedBorrowingsByUserId(Integer id) throws NotFoundException {
        Set<BorrowingWithAllDTO> unReturnedBorrowDtos = new HashSet<>();
        Set<Borrowing> borrowings = borrowingDao.findBorrowingByUser_IdAndAndReturnedIsFalse(id);
        for (Borrowing borrow : borrowings) {
            int idBorrow = borrow.getId();
            unReturnedBorrowDtos.add(this.findByIdWithAll(borrow.getId()));
        }
        return unReturnedBorrowDtos;
    }

    public Boolean bookRentable(Integer userId, Integer bookId) throws NotFoundException {
        Boolean rentable = true;
        Set<BorrowingWithAllDTO> borrowDtos = this.findUnReturnedBorrowingsByUserId(userId);
        for (BorrowingWithAllDTO borrowingWithAllDTO : borrowDtos) {
            if (borrowingWithAllDTO.getCopy().getBook().getId() == bookId) {
                rentable = false;
            }
        }
        return rentable;
    }


    public ResponseEntity extendByIdWithAll(Integer id) {
        LocalDate now = LocalDate.now();
        Borrowing borrow = borrowingDao.findById(id).get();
        if (borrow.getReturnDate().compareTo(now) > 0) {
            borrow.setReturnDate(borrow.getReturnDate().plusMonths(1));
            borrow.setExtended(true);
            BorrowingWithAllDTO borrowing = borrowingWithAllToDTOMapper.getDestination(borrow);
            this.save(borrowing);
            return new ResponseEntity(borrowing,HttpStatus.OK);
        }else {
            borrow.setExtended(true);
            BorrowingWithAllDTO borrowing = borrowingWithAllToDTOMapper.getDestination(borrow);
            this.save(borrowing);
        return new ResponseEntity(borrowing, HttpStatus.FORBIDDEN);
        }
    }

    public Boolean isBorrowingLate(Integer id) {
        LocalDate now = LocalDate.now();
        Borrowing borrow = borrowingDao.findById(id).get();
        BorrowingWithAllDTO borrowing = borrowingWithAllToDTOMapper.getDestination(borrow);
        if (borrowing.getReturnDate().compareTo(now) < 0) {
            borrowing.setExtended(true);
            this.save(borrowing);
            return true;
        }
        return false;
    }

    public Set<BorrowingLateDTO> findAllTooLateBorrowings() {
        Set<Borrowing> borrowings = borrowingDao.findBorrowingByReturnedFalse();
        Set<BorrowingLateDTO> lateBorrowings = new HashSet<>();
        LocalDate today = LocalDate.now();
        for (Borrowing borrowing : borrowings) {
            if (borrowing.getReturnDate().compareTo(today) < 0) {
                lateBorrowings.add(borrowingLateToDTOMapper.getDestination(borrowing));

            }
        }
        return lateBorrowings;
    }


    public ResponseEntity returnBorrowing(Integer id, UserDto employee) {
        Borrowing borrow = borrowingDao.findById(id).get();
        borrow.setReturned(true);
        CopyWithALLDTO copy = copyWithAllToDTOMapper.getDestination(borrow.getCopy());
        copy.setAvailable(true);
        copyService.save(copy);
        BorrowingWithAllDTO borrowing = borrowingWithAllToDTOMapper.getDestination(borrow);
        borrowing.setReturningEmployee(employee);
        this.save(borrowing);
        if (waitListService.isWaitListed(copy.getBook().getId())) {
            waitListService.availableBookofWaitLists(borrowing.getCopy().getBook().getId());
        }
        return new ResponseEntity(borrowing, HttpStatus.OK);
    }

    public BorrowingWithAllDTO addBorrowing(BorrowingWithAllDTO borrowingWithAllDTO) {
        borrowingWithAllDTO.setBorrowingDate(LocalDate.now());
        borrowingWithAllDTO.setReturnDate(LocalDate.now().plusMonths(1));
        borrowingWithAllDTO.setReturned(false);
        copyService.setUnavailableCopy(borrowingWithAllDTO.getCopy());
        return borrowingWithAllToDTOMapper.getDestination(borrowingDao.save(dtoToBorrowingWithAllMapper.getDestination(borrowingWithAllDTO)));
    }

    public ResponseEntity deleteBorrowing(BorrowingWithAllDTO borrowingWithAllDTO) {
        try {
            Borrowing borrow = borrowingDao.findById(borrowingWithAllDTO.getId()).get();
            CopyWithALLDTO copy = copyWithAllToDTOMapper.getDestination(borrow.getCopy());
            copy.setAvailable(true);
            copyService.save(copy);
            borrowingDao.delete(borrow);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Il n'y a pas d'emprunt pour cette personne, ou ce livre", HttpStatus.NOT_FOUND);
        }
    }

    public LocalDate findBorrowingsByBookId(Integer bookId) {
        List<CopyDto> copies = copyService.findCopiesByBook_Id(bookId);
        List<BorrowingWithAllDTO> rentCopies = new ArrayList<>();
        String firstDate;
        for (CopyDto copy : copies) {
            Borrowing borrow = borrowingDao.findBorrowingByCopy_IdAndReturnedIsFalse(copy.getId());
            BorrowingWithAllDTO borrowingDto = borrowingWithAllToDTOMapper.getDestination(borrow);
            rentCopies.add(borrowingDto);
        }
        LocalDate firstDateOfBorrow = rentCopies.get(0).getReturnDate();
        for (BorrowingWithAllDTO borrowingWithAllDTO : rentCopies) {
            LocalDate date = borrowingWithAllDTO.getReturnDate();
            if (date.compareTo(firstDateOfBorrow) < 0) {
                firstDateOfBorrow = date;

            }
        }
        return firstDateOfBorrow;
    }


}
