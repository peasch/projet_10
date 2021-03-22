package com.peasch.service;

import com.peasch.model.dto.libraries.LibraryDto;

import java.util.List;

public interface LibraryService {
    List<LibraryDto> getLibraries();


    LibraryDto findById(Integer id);

    LibraryDto save(LibraryDto libraryDto);
}
