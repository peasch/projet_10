package com.peasch.model.dto.copies;

import com.googlecode.jmapper.annotations.JGlobalMap;

import java.io.Serializable;
@JGlobalMap
public class CopyDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private boolean available;



    public CopyDto() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
