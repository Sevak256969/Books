package com.example.demo.business.service;

import com.example.demo.business.model.NewPublisher;
import com.example.demo.business.model.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherService {

    List<Publisher> getAllPublisher();

    Optional<Publisher> getPublisherById(Integer id);

    Publisher createPublisher(NewPublisher publisher);

    Publisher updatePublisher(Integer id, Publisher publisher);

    void deletePublisher(Integer id);


}
