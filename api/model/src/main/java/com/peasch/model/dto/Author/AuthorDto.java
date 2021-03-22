package com.peasch.model.dto.Author;
import com.googlecode.jmapper.annotations.JGlobalMap;
import com.peasch.model.dto.Book.BookDto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@JGlobalMap
public class AuthorDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String firstName;
    private String birthDate;
    private String deathDate;


    public AuthorDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }


}
