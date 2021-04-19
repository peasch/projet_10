package com.peasch.service;

import com.peasch.model.dto.copies.CopyDto;
import com.peasch.model.dto.copies.CopyWithALLDTO;
import com.peasch.model.entities.Copy;
import javassist.NotFoundException;

import java.util.List;
import java.util.Map;

public interface CopyService {

    List<CopyDto> getCopies();

    CopyDto findById(Integer id);

    CopyDto save(CopyWithALLDTO copy);

    List<CopyDto> findCopiesByBook_IdAndAvailable(Integer bookId,Integer libId);
    Map<Integer,Integer> findCopiesInLibrary(Integer bookId);
    CopyWithALLDTO findByCopyWithAll(Copy copy) throws NotFoundException;
    CopyWithALLDTO setUnavailableCopy( CopyWithALLDTO copy);
    int findNumberOfCopiesAvailable(Integer bookId);
    List<CopyDto> findCopiesByBook_Id(Integer bookId);
    int findNumberOfCopies(Integer bookId);
    boolean isCopyAvailable (int Id);
}

