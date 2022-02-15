package com.example.demo.application.controller;

import com.example.demo.application.dto.NewPublicationDto;
import com.example.demo.application.dto.PublicationDto;
import com.example.demo.business.model.NewPublication;
import com.example.demo.business.model.Publication;
import com.example.demo.business.service.PublicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<PublicationDto> getPublication() {

        List<PublicationDto> publicationList = publicationService.getAllPublication()
                .stream()
                .map(publication -> modelMapper.map(publication, PublicationDto.class))
                .collect(Collectors.toList());

        return publicationList;
    }

    @GetMapping(value = "/{id}")
    public Optional<PublicationDto> getPublicationById(@PathVariable("id") Integer id) {

      return publicationService.getPublicationById(id).map(publicationEntity -> modelMapper.map(publicationEntity, PublicationDto.class));

    }

    @PostMapping
    public PublicationDto creatPublication(@RequestBody @Valid NewPublicationDto publicationDto){
        NewPublication publication = modelMapper.map(publicationDto, NewPublication.class);
        Publication publication2 = publicationService.createPublication(publication);
        return modelMapper.map(publication2, PublicationDto.class);
    }

    @PutMapping("/{id}")
    public PublicationDto updatePublication(@RequestBody NewPublicationDto publicationDto, @PathVariable("id") Integer id){
        Publication publication = modelMapper.map(publicationDto, Publication.class);
        Publication publication2 = publicationService.updatePublication(id, publication);
        return modelMapper.map(publication2, PublicationDto.class);
    }

    @DeleteMapping("/{id}")
    public void deletePublication(@PathVariable("id") Integer id) {

        publicationService.deletePublication(id);
    }
}
