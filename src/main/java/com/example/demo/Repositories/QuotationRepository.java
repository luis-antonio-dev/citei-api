package com.example.demo.Repositories;

import com.example.demo.Models.Quotation;
import com.example.demo.Models.Source;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuotationRepository extends CrudRepository<Quotation, Long> {
    @Query("SELECT q FROM Quotation q WHERE q.content LIKE CONCAT('%',:content,'%')")
    Iterable<Quotation> findByContentLike(@Param("content") String content);
}
