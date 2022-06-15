package com.example.demo.Repositories;

import com.example.demo.Models.Quotation;
import org.springframework.data.repository.CrudRepository;

public interface QuotationRepository extends CrudRepository<Quotation, Long> {
}
