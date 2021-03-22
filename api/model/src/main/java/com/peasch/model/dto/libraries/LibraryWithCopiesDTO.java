package com.peasch.model.dto.libraries;

import com.peasch.model.dto.copies.CopyDto;

import java.util.HashSet;
import java.util.Set;

public class LibraryWithCopiesDTO extends LibraryDto {
    private Set<CopyDto> copies = new HashSet<>();

    public LibraryWithCopiesDTO() {
    }


    public Set<CopyDto> getCopies() {
        return copies;
    }


    public void setCopies(Set<CopyDto> copies) {
        this.copies = copies;
    }
}
