package com.example.demo.business.service.impl;

import com.example.demo.business.exception.PublisherRuntimeException;
import com.example.demo.business.model.NewPublisher;
import com.example.demo.business.model.Publisher;
import com.example.demo.business.service.PublisherService;
import com.example.demo.data.entity.PublisherEntity;
import com.example.demo.data.repository.PublisherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherRepository publisherRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Publisher> getAllPublisher() {
        List<Publisher> publishers = publisherRepo.findAll()
                .stream()
                .map(publisherEntity -> modelMapper.map(publisherEntity, Publisher.class))
                .collect(Collectors.toList());
        return publishers;
    }

    @Override
    public Optional<Publisher> getPublisherById(Integer id) {
        return publisherRepo.findById(id).map(publisherEntity -> modelMapper.map(publisherEntity, Publisher.class));
    }

    @Override
    public Publisher createPublisher(NewPublisher publisher) {
        try {
            PublisherEntity publisherEntity = modelMapper.map(publisher, PublisherEntity.class);
            PublisherEntity publisherEntity2 = publisherRepo.save(publisherEntity);
            return modelMapper.map(publisherEntity2, Publisher.class);
        } catch (Exception e) {
            throw new PublisherRuntimeException("Eee", e);
        }

    }

    @Override
    public Publisher updatePublisher(Integer id, Publisher publisher) {
        publisher.setId(id);
        PublisherEntity publisherEntity = modelMapper.map(publisher, PublisherEntity.class);
        PublisherEntity publisherEntity2  = publisherRepo.save(publisherEntity);
        return modelMapper.map(publisherEntity2, Publisher.class);
    }

    @Override
    public void deletePublisher(Integer id) {

        publisherRepo.deleteById(id);
    }
}
