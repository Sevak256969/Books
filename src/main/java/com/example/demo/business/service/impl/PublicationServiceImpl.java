package com.example.demo.business.service.impl;

import com.example.demo.business.exception.PublicationRuntimeException;
import com.example.demo.business.model.NewPublication;
import com.example.demo.business.model.Publication;
import com.example.demo.business.service.PublicationService;
import com.example.demo.data.entity.PublicationEntity;
import com.example.demo.data.repository.PublicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImpl implements PublicationService {
    @Autowired
    private PublicationRepository publicationRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Publication> getAllPublication() {
        List<Publication> publications = publicationRepo.findAll()
                .stream()
                .map(publicationEntity -> modelMapper.map(publicationEntity, Publication.class))
                .collect(Collectors.toList());
        return publications;
    }

    @Override
    public Optional<Publication> getPublicationById(Integer id) {
        return publicationRepo.findById(id).map(publicationEntity -> modelMapper.map(publicationEntity, Publication.class));
    }

    @Override
    public Publication createPublication(NewPublication publication) {
        try {
            PublicationEntity publicationEntity = modelMapper.map(publication, PublicationEntity.class);
            PublicationEntity publicationEntity2 = publicationRepo.save(publicationEntity);
            return modelMapper.map(publicationEntity2, Publication.class);
        } catch (Exception e) {
            throw new PublicationRuntimeException("Eee", e);
        }

    }

    @Override
    public Publication updatePublication(Integer id, Publication publication) {
        publication.setId(id);
        PublicationEntity publicationEntity = modelMapper.map(publication, PublicationEntity.class);
        PublicationEntity publicationEntity2  = publicationRepo.save(publicationEntity);
        return modelMapper.map(publicationEntity2, Publication.class);
    }

    @Override
    public void deletePublication(Integer id) {

        publicationRepo.deleteById(id);
    }
}
