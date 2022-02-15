package com.example.demo.data.repository;

import com.example.demo.data.entity.PublisherEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends CrudRepository<PublisherEntity,Integer> {
    List<PublisherEntity> findAll();
}
