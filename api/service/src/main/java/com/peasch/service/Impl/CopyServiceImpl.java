package com.peasch.service.Impl;

import com.googlecode.jmapper.JMapper;
import com.peasch.model.dto.Book.BookDto;
import com.peasch.model.dto.Book.BookWithoutCopiesDTO;
import com.peasch.model.dto.copies.CopyDto;
import com.peasch.model.dto.copies.CopyWithALLDTO;
import com.peasch.model.dto.libraries.LibraryDto;
import com.peasch.model.entities.Book;
import com.peasch.model.entities.Copy;
import com.peasch.model.entities.Library;
import com.peasch.repository.dao.CopyDao;
import com.peasch.service.BookService;
import com.peasch.service.CopyService;
import com.peasch.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CopyServiceImpl implements CopyService {
    @Autowired
    private CopyDao copyDao;
    @Autowired
    private JMapper<CopyDto, Copy> copyJMapper;

    @Autowired
    private JMapper<Copy, CopyDto>  dtoToCopyMapper;
    @Autowired
    private JMapper<CopyWithALLDTO, Copy> copyWithAllToDTOMapper;
    @Autowired
    private JMapper<BookDto, Book>  bookToDTOMapper;
    @Autowired
    private JMapper<BookWithoutCopiesDTO, Book>  bookWithoutCopiesDTOBookJMapper;
    @Autowired
    private JMapper<LibraryDto, Library>  libraryToDTOMapper;
    @Autowired
    private JMapper<Copy, CopyWithALLDTO>  dtoToCopyWithAllMapper;
    @Autowired
    private LibraryService libService;

    @Autowired
    private BookService bookService;

    public List<CopyDto> getCopies() {
        List<Copy> copies = copyDao.findAll();
        return copies.stream().map(x -> copyJMapper.getDestination(x)).collect(Collectors.toList());
    }

    public CopyWithALLDTO findById(Integer id) {
        Copy copy = copyDao.findById(id).get();
        CopyWithALLDTO copyDto= copyWithAllToDTOMapper.getDestination(copy);
        copyDto.setBook(bookWithoutCopiesDTOBookJMapper.getDestination(copy.getBook()));
        copyDto.setLibrary(libraryToDTOMapper.getDestination(copy.getLibrary()));
        return copyDto;

    }

    public CopyWithALLDTO findByCopyWithAll(Copy copy) {
        CopyWithALLDTO copyDto = copyWithAllToDTOMapper.getDestination(copy);
        copyDto.setBook(bookService.findById(copy.getBook().getId()));
        copyDto.setLibrary(libService.findById(copy.getLibrary().getId()));
        return copyDto;

    }

    public CopyDto save(CopyWithALLDTO copy) {
        return copyJMapper.getDestination(copyDao.save(dtoToCopyWithAllMapper.getDestination(copy)));
    }


    public List<CopyDto> findCopiesByBook_IdAndAvailable(Integer bookId, Integer libId) {
        List<Copy> copies = copyDao.findCopiesByBook_IdAndAvailableAndLibrary_Id(bookId, true, libId);
        return copies.stream().map(x -> copyJMapper.getDestination(x)).collect(Collectors.toList());
    }

    public Map<Integer, Integer> findCopiesInLibrary(Integer bookId) {
        List<LibraryDto> libraries = libService.getLibraries();
        Map<Integer, Integer> copiesInLibraries = new HashMap<>();
        for (LibraryDto library : libraries) {
            copiesInLibraries.put(library.getId(), this.findCopiesByBook_IdAndAvailable(bookId, library.getId()).size());
        }
        return copiesInLibraries;
    }

    public CopyWithALLDTO setUnavailableCopy( CopyWithALLDTO copy){
        CopyWithALLDTO copyWithALLDTO = this.findById(copy.getId());
        copyWithALLDTO.setAvailable(false);
        this.save(copyWithALLDTO);
        return copyWithALLDTO;
    }
}
