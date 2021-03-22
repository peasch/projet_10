package com.peasch.service.Impl;

import com.googlecode.jmapper.JMapper;
import com.peasch.model.dto.libraries.LibraryDto;
import com.peasch.model.entities.Library;
import com.peasch.repository.dao.LibraryDao;
import com.peasch.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    private LibraryDao libraryDao;

    @Autowired
    private JMapper<LibraryDto, Library> libraryToDTOMapper;

    @Autowired
    private JMapper<Library, LibraryDto>  dtoToLibraryMapper;

    public List<LibraryDto> getLibraries(){
        List<Library> libraries = libraryDao.findAll();
        return libraries.stream().map(x->libraryToDTOMapper.getDestination(x)).collect(Collectors.toList());
    }

    public LibraryDto findById(Integer id){

        return libraryToDTOMapper.getDestination(libraryDao.findById(id).get());

    }

    public LibraryDto save(LibraryDto libraryDto){
        return libraryToDTOMapper.getDestination(libraryDao.save(dtoToLibraryMapper.getDestination(libraryDto)));
    }

}
