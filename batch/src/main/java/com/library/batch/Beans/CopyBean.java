package com.library.batch.Beans;

public class CopyBean {
    private static final long serialVersionUID = 1L;
    private int id;
    private boolean available;
    private BookBean book;
    private LibraryBean library;

    public CopyBean() {
    }

    @Override
    public String toString() {
        return "CopyBean{" +
                "id=" + id +
                ", available=" + available +
                ", book=" + book +
                ", library=" + library +
                '}';
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

    public BookBean getBook() {
        return book;
    }

    public void setBook(BookBean book) {
        this.book = book;
    }

    public LibraryBean getLibrary() {
        return library;
    }

    public void setLibrary(LibraryBean library) {
        this.library = library;
    }
}
