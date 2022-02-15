package com.example.demo.business.service;

import com.example.demo.business.model.NewPublication;
import com.example.demo.business.model.Publication;

import java.util.List;
import java.util.Optional;

public interface PublicationService {

    List<Publication> getAllPublication();

    Optional<Publication> getPublicationById(Integer id);

    Publication createPublication(NewPublication publication);

    Publication updatePublication(Integer id, Publication publication);

    void deletePublication(Integer id);


}
