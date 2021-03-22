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
import com.peasch.model.entities.User;
import com.peasch.repository.dao.BorrowingDao;
import com.peasch.service.BorrowingService;
import com.peasch.service.CopyService;
import com.peasch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
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
    private JMapper<Borrowing, BorrowingWithAllDTO>  dtoToBorrowingWithAllMapper;

    @Autowired
    private JMapper<CopyDto, Copy>  copyToDTOMapper;
    @Autowired
    private JMapper<Copy, CopyDto>  dtoToCopyMapper;
    @Autowired
    private JMapper<CopyWithALLDTO, Copy>  copyWithAllToDTOMapper;
    @Autowired
    private JMapper<Copy, CopyWithALLDTO>  dtoToCopyWithAllMapper;
    @Autowired
    private CopyService copyService;



    public List<BorrowingDto> getBorrowings(){
        List<Borrowing>borrowings = borrowingDao.findAll();
       return borrowings.stream().map(x->borrowingToDTOMapper.getDestination(x)).collect(Collectors.toList());
    }


    public BorrowingDto findById(Integer id){
        BorrowingDto borrowingDto = borrowingToDTOMapper.getDestination(borrowingDao.findById(id).get());
        return borrowingDto;

    }
    public BorrowingWithAllDTO findByIdWithAll(Integer id){
        Borrowing borrow =borrowingDao.findById(id).get();
        BorrowingWithAllDTO borrowingDto = borrowingWithAllToDTOMapper.getDestination(borrow);
        borrowingDto.setCopy(copyService.findByCopyWithAll(borrow.getCopy()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        String returnDate = borrow.getReturnDate();
        LocalDate date = LocalDate.parse(returnDate);
        LocalDate today =LocalDate.now();
        Period period = Period.between(today,date);
        int fromMonths = period.getMonths();
        borrowingDto.setDaysToGo(fromMonths*30 + period.getDays());
        return borrowingDto;

    }


    public BorrowingWithAllDTO save(BorrowingWithAllDTO borrowingWithAllDTO){

        return borrowingWithAllToDTOMapper.getDestination( borrowingDao.save(dtoToBorrowingWithAllMapper.getDestination(borrowingWithAllDTO)));
    }


    public Set<BorrowingWithAllDTO> findBorrowingsByUserId(Integer id) {
        Set<BorrowingWithAllDTO> borrowDtos = new HashSet<>();
    Set<Borrowing> borrowings = borrowingDao.findBorrowingByUser_Id(id);
    for (Borrowing borrow : borrowings){
        borrowDtos.add(this.findByIdWithAll(borrow.getId()));
    }
    return borrowDtos;
    }



    public BorrowingWithAllDTO extendByIdWithAll(Integer id){
        Borrowing borrow =borrowingDao.findById(id).get();
        String returnDate = borrow.getReturnDate();
        LocalDate date = LocalDate.parse(returnDate);
        date = date.plusMonths(1);
        borrow.setReturnDate(date.toString());
        borrow.setExtended(true);
        BorrowingWithAllDTO borrowing =borrowingWithAllToDTOMapper.getDestination(borrow);
        this.save(borrowing);
        return borrowing;

    }

    public Set<BorrowingLateDTO> findAllTooLateBorrowings(){
        Set<Borrowing> borrowings = borrowingDao.findBorrowingByReturnedFalse();
        Set<BorrowingLateDTO> lateBorrowings = new HashSet<>();
        LocalDate today =LocalDate.now();
        for(Borrowing borrowing :borrowings){
            String returnDate = borrowing.getReturnDate();
            System.out.println(today);
            LocalDate date = LocalDate.parse(returnDate);
            System.out.println(date);
            if (date.compareTo(today)<0){
               lateBorrowings.add(borrowingLateToDTOMapper.getDestination(borrowing));

            }
        }
        return lateBorrowings;
    }


    public BorrowingWithAllDTO returnBorrowing(Integer id,UserDto employee){
        Borrowing borrow =borrowingDao.findById(id).get();
        borrow.setReturned(true);
        CopyWithALLDTO copy = copyWithAllToDTOMapper.getDestination(borrow.getCopy());
        copy.setAvailable(true);
        copyService.save(copy);
        BorrowingWithAllDTO borrowing =borrowingWithAllToDTOMapper.getDestination(borrow);
        borrowing.setReturningEmployee(employee);
        this.save(borrowing);
        return  borrowing;
    }

    public BorrowingWithAllDTO addBorrowing (UserDto user, CopyWithALLDTO copyDTO){
        BorrowingWithAllDTO borrowing = new BorrowingWithAllDTO();
        borrowing.setUser(user);
        borrowing.setCopy(copyDTO);
        borrowing.setBorrowingDate(LocalDate.now().toString());
        borrowing.setReturnDate(LocalDate.now().plusMonths(1).toString());
        borrowing.setReturned(false);
        copyService.setUnavailableCopy(copyDTO);
        return borrowingWithAllToDTOMapper.getDestination(borrowingDao.save(dtoToBorrowingWithAllMapper.getDestination(borrowing)));
    }
}
