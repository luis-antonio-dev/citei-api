package com.example.demo.Repositories;

import com.example.demo.Models.Quotation;
import com.example.demo.Models.Source;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuotationRepository extends CrudRepository<Quotation, Long> {
    List<Quotation> findByContent(String content);
}
