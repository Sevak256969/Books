package com.example.demo.application.dto;

//@Getter
//@NoArgsConstructor
public class BookDto {

    private Integer id;
    private String author;
    private String name;
    private Integer yearWhenWritten;
    private Integer numberOfPages;
    private String genre;

    public BookDto(Integer id, String author, String name, Integer yearWhenWritten, Integer numberOfPages, String genre) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.yearWhenWritten = yearWhenWritten;
        this.numberOfPages = numberOfPages;
        this.genre = genre;
    }

    public BookDto(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setYearWhenWritten(Integer yearWhenWritten) {
        this.yearWhenWritten = yearWhenWritten;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
