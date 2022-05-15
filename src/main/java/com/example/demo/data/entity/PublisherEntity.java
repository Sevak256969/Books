package com.example.demo.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "publisher")
public class PublisherEntity {
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO )
    private Integer id;
    private String publisher;
    private String publisherCode;

    public PublisherEntity(){}

    public PublisherEntity(Integer id, String publisher, String publisherCode) {
        this.id = id;
        this.publisher = publisher;
        this.publisherCode = publisherCode;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getPublisherCode() {
        return publisherCode;
    }
    public void setPublisherCode(String publisherCode) {
        this.publisherCode = publisherCode;
    }
}
