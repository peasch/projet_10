package com.peasch.model.dto.Book;
import com.googlecode.jmapper.annotations.JGlobalMap;
import java.io.Serializable;


@JGlobalMap
public class BookDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private String summary;
    private Boolean available;

    private String cover;


    public BookDto() {
    }


    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
