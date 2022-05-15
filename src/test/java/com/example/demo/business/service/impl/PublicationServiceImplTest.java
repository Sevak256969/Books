package com.example.demo.business.service.impl;

import com.example.demo.business.exception.PublicationRuntimeException;
import com.example.demo.business.model.Publication;
import com.example.demo.business.service.PublicationService;
import com.example.demo.data.entity.PublicationEntity;
import com.example.demo.data.repository.PublicationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
public class PublicationServiceImplTest {

    @Mock
    private PublicationRepository publicationRepo;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private PublicationService publicationService = new PublicationServiceImpl();

    @Test
    public void get_all_publication_ok() {
        //mocked test data
        List<PublicationEntity> publications = new ArrayList<>();
        PublicationEntity publication1 = new PublicationEntity(1,1922,123,"Arm");
        PublicationEntity publication2 = new PublicationEntity(2,1899,67,"Arm");
        PublicationEntity publication3 = new PublicationEntity(3,1567,5,"Arm");
        PublicationEntity publication4 = new PublicationEntity(4,1234,60,"Arm");

        publications.add(publication1);
        publications.add(publication2);
        publications.add(publication3);
        publications.add(publication4);

        Publication publication5 = new Publication(1,1922,123,"Arm");
        Publication publication6 = new Publication(2,1899,67,"Arm");
        Publication publication7 = new Publication(3,1567,5,"Arm");
        Publication publication8 = new Publication(4,1234,60,"Arm");

        when(publicationRepo.findAll()).thenReturn(publications);
        when(modelMapper.map(publication1,Publication.class)).thenReturn(publication5);
        when(modelMapper.map(publication2,Publication.class)).thenReturn(publication6);
        when(modelMapper.map(publication3,Publication.class)).thenReturn(publication7);
        when(modelMapper.map(publication4,Publication.class)).thenReturn(publication8);

        //test- call the method that u test
        List<Publication> publicationList = publicationService.getAllPublication();

        //assertion - validate the results
        assertEquals(4, publicationList.size());
        Publication publication = publicationList.get(0);
        assertEquals(1, publication.getId());
        assertEquals(1922, publication.getPublicationYear());
        assertEquals(123, publication.getRating());
        assertEquals("Arm", publication.getLanguage());

        verify(publicationRepo, times(1)).findAll();
    }

    @Test
    public void get_publication_by_id_ok() {

        PublicationEntity publicationEntity = new PublicationEntity(1,1922,123,"Arm");
        Publication publication = new Publication(1,1922,123,"Arm");

        when(publicationRepo.findById(1)).thenReturn(Optional.of(publicationEntity));
        when(modelMapper.map(any(), any())).thenReturn(publication, Publication.class);

        Optional<Publication> publication2 = publicationService.getPublicationById(1);

        assertTrue(publication2.isPresent());
        assertEquals(1, publication2.get().getId());
        verify(publicationRepo, times(1)).findById(1);
    }

    @Test
    public void create_employee_exception() {

        Publication publication = new Publication(1,1922,123,"Arm");
        PublicationEntity publicationEntity = new PublicationEntity( 1,1922,123,"Arm");
        when(modelMapper.map(any(), any())).thenReturn(publicationEntity);
        when(publicationRepo.save(any())).thenThrow(RuntimeException.class);

        Assertions.assertThrows(PublicationRuntimeException.class, () ->{
            publicationService.createPublication(publication);
        }, "Was expending to save PublicationRuntimeException but didn't save");
    }

    @Test
    public void create_publication_ok() {

        Publication publication = new Publication(1,1922, 123, "Arm");
        PublicationEntity publicationEntity = modelMapper.map(publication, PublicationEntity.class);
        Publication publication1 = new Publication(1,1922, 123, "Arm");

        when(publicationRepo.save(publicationEntity)).thenReturn(publicationEntity);
        when(modelMapper.map(publication, PublicationEntity.class)).thenReturn(publicationEntity);
        when(modelMapper.map(publicationEntity, Publication.class)).thenReturn(publication1);

        Publication publication2 = publicationService.createPublication(publication);

        assertEquals(1, publication2.getId());
        assertEquals(1922, publication2.getPublicationYear());
        assertEquals(123, publication2.getRating());
        assertEquals("Arm", publication2.getLanguage());

        verify(publicationRepo, times(1)).save(publicationEntity);
    }

    @Test
    public void update_publication_ok() {

        Publication publication = new Publication(1,1922, 123, "Arm");
        PublicationEntity publicationEntity = modelMapper.map(publication, PublicationEntity.class);
        Publication publication1 = new Publication(1,1922, 123, "Arm");

        when(publicationRepo.save(publicationEntity)).thenReturn(publicationEntity);
        when(modelMapper.map(publication,PublicationEntity.class)).thenReturn(publicationEntity);
        when(modelMapper.map(publicationEntity,Publication.class)).thenReturn(publication1);

        Publication publication2 = publicationService.updatePublication(1, publication1);

        assertEquals(1, publication2.getId());
        assertEquals(1922, publication2.getPublicationYear());
        assertEquals(123, publication2.getRating());
        assertEquals("Arm", publication2.getLanguage());

        verify(publicationRepo, times(1)).save(publicationEntity);
    }

    @Test
    public void delete_publication_ok() {

        final Integer id = 1;
        publicationRepo.deleteById(id);
    }
}
