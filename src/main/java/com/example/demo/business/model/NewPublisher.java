package com.example.demo.business.model;

public class NewPublisher {

    private String publisher;
    private String publisherCode;

    public NewPublisher() {
    }

    public NewPublisher(String publisher, String publisherCode) {

        this.publisher = publisher;
        this.publisherCode = publisherCode;
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
