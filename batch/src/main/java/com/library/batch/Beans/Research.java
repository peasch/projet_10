package com.library.batch.Beans;

public class Research {

    private String researchTitle;
    private String researchAuthor;
    private String researchCategory;

    public Research() {
    }

    public String getResearchTitle() {
        return researchTitle;
    }

    public void setResearchTitle(String researchTitle) {
        this.researchTitle = researchTitle;
    }

    public String getResearchAuthor() {
        return researchAuthor;
    }

    public void setResearchAuthor(String researchAuthor) {
        this.researchAuthor = researchAuthor;
    }

    public String getResearchCategory() {
        return researchCategory;
    }

    public void setResearchCategory(String researchCategory) {
        this.researchCategory = researchCategory;
    }

    @Override
    public String toString() {
        return "Research{" +
                "researchTitle='" + researchTitle + '\'' +
                ", researchAuthor='" + researchAuthor + '\'' +
                ", researchCategory='" + researchCategory + '\'' +
                '}';
    }
}
