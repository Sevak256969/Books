package com.example.demo.data.repository;

import com.example.demo.data.entity.BooksEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends CrudRepository< BooksEntity,Integer> {
    List<BooksEntity> findAll();
}
