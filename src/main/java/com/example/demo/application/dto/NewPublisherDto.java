package com.example.demo.application.dto;

//@Data
//@NoArgsConstructor
public class NewPublisherDto {

    private String publisher;

    private String publisherCode;

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
