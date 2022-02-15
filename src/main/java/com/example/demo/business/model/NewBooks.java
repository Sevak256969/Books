package com.example.demo.business.model;

public class NewBooks {

    private  String author;
    private  String name;
    private  Integer yearWhenWritten;
    private  Integer numberOfPages;
    private  String genre;

    public NewBooks() {

    }

    public NewBooks(String author, String name, Integer yearWhenWritten, Integer numberOfPages, String genre) {
        this.author = author;
        this.name = name;
        this.yearWhenWritten = yearWhenWritten;
        this.numberOfPages = numberOfPages;
        this.genre = genre;
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
