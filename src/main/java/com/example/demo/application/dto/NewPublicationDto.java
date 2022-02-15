package com.example.demo.application.dto;


import javax.validation.constraints.NotNull;

//@Data
//@NoArgsConstructor
public class NewPublicationDto {
    @NotNull(message = "publicationYear of the category can't be null")
    private Integer publicationYear;

    @NotNull(message = "rating of the category can't be null")
    private Integer rating;

    @NotNull(message = "language of the category can't be null")
    private String language;

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
