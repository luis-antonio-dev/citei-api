package com.example.demo.Repositories;

import com.example.demo.Models.Source;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SourceRepository extends CrudRepository<Source,Long> {
    List<Source> findByTitle(String title);

}
