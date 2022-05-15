package com.example.demo.application.dto;

public class PublicationDto {

    private Integer id;
    private Integer publicationYear;
    private Integer rating;
    private String language;

    public PublicationDto(Integer id, Integer publicationYear, Integer rating, String language) {
        this.id = id;
        this.publicationYear = publicationYear;
        this.rating = rating;
        this.language = language;
    }

    public PublicationDto() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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
