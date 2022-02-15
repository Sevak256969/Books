package com.example.demo.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "publication")
public class PublicationEntity {

    @Id
    @GeneratedValue( strategy= GenerationType.AUTO )
//    @Column(name = "id")
    private Integer id;
    private Integer publicationYear;
    private Integer rating;
    private String language;

    public PublicationEntity(){}

    public PublicationEntity(Integer id, Integer publicationYear, Integer rating, String language) {
        this.id = id;
        this.publicationYear = publicationYear;
        this.rating = rating;
        this.language = language;
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
