package com.example.demo.data.repository;

import com.example.demo.data.entity.PublicationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PublicationRepository extends CrudRepository<PublicationEntity,Integer> {
    List<PublicationEntity> findAll();
}
