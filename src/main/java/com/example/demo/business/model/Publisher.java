package com.example.demo.business.model;

public class Publisher {

    private Integer id;
    private String publisher;
    private String publisherCode;

    public Publisher() {
    }

    public Publisher(Integer id, String publisher, String publisherCode) {
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
