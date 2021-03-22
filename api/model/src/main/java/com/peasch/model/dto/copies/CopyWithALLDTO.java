package com.peasch.model.dto.copies;

import com.peasch.model.dto.Book.BookDto;
import com.peasch.model.dto.Book.BookWithoutCopiesDTO;
import com.peasch.model.dto.libraries.LibraryDto;

public class CopyWithALLDTO extends CopyDto{
    private BookWithoutCopiesDTO book;
    private LibraryDto library;

    public CopyWithALLDTO() {
    }

    public BookWithoutCopiesDTO getBook() {
        return book;
    }

    public void setBook(BookWithoutCopiesDTO book) {
        this.book = book;
    }

    public LibraryDto getLibrary() {
        return library;
    }

    public void setLibrary(LibraryDto library) {
        this.library = library;
    }
}
