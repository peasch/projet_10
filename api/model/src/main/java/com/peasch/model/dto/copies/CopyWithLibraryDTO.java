package com.peasch.model.dto.copies;

import com.peasch.model.dto.libraries.LibraryDto;

public class CopyWithLibraryDTO {
    private LibraryDto library;

    public CopyWithLibraryDTO() {
    }

    public LibraryDto getLibrary() {
        return library;
    }

    public void setLibrary(LibraryDto library) {
        this.library = library;
    }
}
