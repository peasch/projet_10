package com.library.batch.Beans;

import java.util.HashSet;
import java.util.Set;

public class BookBean {
    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private String summary;
    private CategoryBean category;
    private AuthorBean author;
    private String cover;
    private Set<CopyBean> copiesOfBook = new HashSet<>();

    public BookBean() {
    }

    @Override
    public String toString() {
        return "BookBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", category=" + category +
                ", author=" + author +
                ", cover='" + cover + '\'' +
                ", copiesOfBook=" + copiesOfBook +
                '}';
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

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Set<CopyBean> getCopiesOfBook() {
        return copiesOfBook;
    }

    public void setCopiesOfBook(Set<CopyBean> copiesOfBook) {
        this.copiesOfBook = copiesOfBook;
    }
}
