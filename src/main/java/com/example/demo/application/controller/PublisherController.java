package com.example.demo.application.controller;

import com.example.demo.application.dto.NewPublisherDto;
import com.example.demo.application.dto.PublisherDto;
import com.example.demo.business.model.NewPublisher;
import com.example.demo.business.model.Publisher;
import com.example.demo.business.service.PublisherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<PublisherDto> getPublisher() {

        List<PublisherDto> publisherList = publisherService.getAllPublisher()
                .stream()
                .map(publisher -> modelMapper.map(publisher, PublisherDto.class))
                .collect(Collectors.toList());

        return publisherList;
    }

    @GetMapping(value = "/{id}")
    public Optional<PublisherDto> getPublisherById(@PathVariable("id") Integer id) {

      return publisherService.getPublisherById(id).map(publisherEntity -> modelMapper.map(publisherEntity, PublisherDto.class));

    }

    @PostMapping
    public PublisherDto creatPublisher(@RequestBody @Valid NewPublisherDto publisherDto){
        NewPublisher publisher = modelMapper.map(publisherDto, NewPublisher.class);
        Publisher publisher2 = publisherService.createPublisher(publisher);
        return modelMapper.map(publisher2, PublisherDto.class);
    }

    @PutMapping("/{id}")
    public PublisherDto updatePublisher(@RequestBody NewPublisherDto publisherDto, @PathVariable("id") Integer id){
        Publisher publisher = modelMapper.map(publisherDto, Publisher.class);
        Publisher publisher2 = publisherService.updatePublisher(id, publisher);
        return modelMapper.map(publisher2, PublisherDto.class);
    }

    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable("id") Integer id) {
        publisherService.deletePublisher(id);
    }
}
