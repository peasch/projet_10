package com.peasch.service;

import com.peasch.model.dto.copies.CopyDto;
import com.peasch.model.dto.copies.CopyWithALLDTO;
import com.peasch.model.entities.Copy;

import java.util.List;
import java.util.Map;

public interface CopyService {

    List<CopyDto> getCopies();

    CopyDto findById(Integer id);

    CopyDto save(CopyWithALLDTO copy);

    List<CopyDto> findCopiesByBook_IdAndAvailable(Integer bookId,Integer libId);
    Map<Integer,Integer> findCopiesInLibrary(Integer bookId);
    CopyWithALLDTO findByCopyWithAll(Copy copy);
    CopyWithALLDTO setUnavailableCopy( CopyWithALLDTO copy);
}

