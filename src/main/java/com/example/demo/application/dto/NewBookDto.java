package com.example.demo.application.dto;

import javax.validation.constraints.NotNull;

//@Data
//@NoArgsConstructor
public class NewBookDto {

    @NotNull(message = "author of the category can't be null")
    private  String author;
    @NotNull(message = "name of the category can't be null")
    private String name;
    @NotNull(message = "yearWhenWritten of the category can't be null")
    private  Integer yearWhenWritten;
    @NotNull(message = "numberOfPages of the category can't be null")
    private Integer numberOfPages;
    @NotNull(message = "genre of the category can't be null")
    private  String genre;

    public NewBookDto(String author, String name, Integer yearWhenWritten, Integer numberOfPages, String genre) {

        this.author = author;
        this.name = name;
        this.yearWhenWritten = yearWhenWritten;
        this.numberOfPages = numberOfPages;
        this.genre = genre;
    }

    public NewBookDto() {
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getYearWhenWritten() {
        return yearWhenWritten;
    }
    public void setYearWhenWritten(int yearWhenWritten) {
        this.yearWhenWritten = yearWhenWritten;
    }
    public Integer getNumberOfPages() {
        return numberOfPages;
    }
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
