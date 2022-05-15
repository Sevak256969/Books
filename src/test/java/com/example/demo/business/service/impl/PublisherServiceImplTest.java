package com.example.demo.business.service.impl;

import com.example.demo.business.exception.PublisherRuntimeException;
import com.example.demo.business.model.Publisher;
import com.example.demo.business.service.PublisherService;
import com.example.demo.data.entity.PublisherEntity;
import com.example.demo.data.repository.PublisherRepository;
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
public class PublisherServiceImplTest {

    @Mock
    private PublisherRepository publisherRepo;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private PublisherService publisherService = new PublisherServiceImpl();

    @Test
    public void get_all_publisher_ok() {
        //mocked test data
        List<PublisherEntity> publishers = new ArrayList<>();
        PublisherEntity publisher1 = new PublisherEntity(1,"Arcax","1-334");
        PublisherEntity publisher2 = new PublisherEntity(2,"Arcax","1-334");
        PublisherEntity publisher3 = new PublisherEntity(3,"Arcax","1-334");
        PublisherEntity publisher4 = new PublisherEntity(4,"Arcax","1-334");

        publishers.add(publisher1);
        publishers.add(publisher2);
        publishers.add(publisher3);
        publishers.add(publisher4);

        Publisher publisher5 = new Publisher(1,"Arcax","1-334");
        Publisher publisher6 = new Publisher(2,"Arcax","1-334");
        Publisher publisher7 = new Publisher(3,"Arcax","1-334");
        Publisher publisher8 = new Publisher(4,"Arcax","1-334");

        when(publisherRepo.findAll()).thenReturn(publishers);
        when(modelMapper.map(publisher1,Publisher.class)).thenReturn(publisher5);
        when(modelMapper.map(publisher2,Publisher.class)).thenReturn(publisher6);
        when(modelMapper.map(publisher3,Publisher.class)).thenReturn(publisher7);
        when(modelMapper.map(publisher4,Publisher.class)).thenReturn(publisher8);

        //test- call the method that u test
        List<Publisher> publisherList = publisherService.getAllPublisher();

        //assertion - validate the results
        assertEquals(4, publisherList.size());
        Publisher publisher = publisherList.get(0);
        assertEquals(1, publisher.getId());
        assertEquals("Arcax", publisher.getPublisher());
        assertEquals("1-334", publisher.getPublisherCode());

        verify(publisherRepo, times(1)).findAll();
    }

    @Test
    public void get_publisher_by_id_ok() {

        PublisherEntity publisherEntity = new PublisherEntity(1,"Arcax","1-334");
        Publisher publisher = new Publisher(1,"Arcax","1-334");

        when(publisherRepo.findById(1)).thenReturn(Optional.of(publisherEntity));
        when(modelMapper.map(any(), any())).thenReturn(publisher, Publisher.class);

        Optional<Publisher> publisher1 = publisherService.getPublisherById(1);

        assertTrue(publisher1.isPresent());
        assertEquals(1, publisher1.get().getId());
        verify(publisherRepo, times(1)).findById(1);
    }

    @Test
    public void create_employee_exception() {

        Publisher publisher = new Publisher(1,"Arcax","1-334");
        PublisherEntity publisherEntity = new PublisherEntity( 1,"Arcax","1-334");
        when(modelMapper.map(any(), any())).thenReturn(publisherEntity);
        when(publisherRepo.save(any())).thenThrow(RuntimeException.class);

        Assertions.assertThrows(PublisherRuntimeException.class, () ->{
            publisherService.createPublisher(publisher);
        }, "Was expending to save PublisherRuntimeException but didn't save");
    }

    @Test
    public void create_publisher_ok() {

        Publisher publisher = new Publisher(1,"Arcax", "1-334");
        PublisherEntity publisherEntity = modelMapper.map(publisher, PublisherEntity.class);
        Publisher publisher1 = new Publisher(1,"Arcax", "1-334");

        when(publisherRepo.save(publisherEntity)).thenReturn(publisherEntity);
        when(modelMapper.map(publisher, PublisherEntity.class)).thenReturn(publisherEntity);
        when(modelMapper.map(publisherEntity, Publisher.class)).thenReturn(publisher1);

        Publisher publisher2 = publisherService.createPublisher(publisher);

        assertEquals(1, publisher2.getId());
        assertEquals("Arcax", publisher2.getPublisher());
        assertEquals("1-334", publisher2.getPublisherCode());

        verify(publisherRepo, times(1)).save(publisherEntity);
    }

    @Test
    public void update_publisher_ok() {

        Publisher publisher = new Publisher(1,"Arcax", "1-334");
        PublisherEntity publisherEntity = modelMapper.map(publisher, PublisherEntity.class);
        Publisher publisher1 = new Publisher(1,"Arcax", "1-334");

        when(publisherRepo.save(publisherEntity)).thenReturn(publisherEntity);
        when(modelMapper.map(publisher,PublisherEntity.class)).thenReturn(publisherEntity);
        when(modelMapper.map(publisherEntity,Publisher.class)).thenReturn(publisher1);

        Publisher publisher2 = publisherService.updatePublisher(1, publisher1);

        assertEquals(1, publisher2.getId());
        assertEquals(1, publisher2.getId());
        assertEquals("Arcax", publisher2.getPublisher());
        assertEquals("1-334", publisher2.getPublisherCode());

        verify(publisherRepo, times(1)).save(publisherEntity);
    }

    @Test
    public void delete_publisher_ok() {

        final Integer id = 1;
        publisherRepo.deleteById(id);
    }
}
