package com.example.demo.Controllers;

import com.example.demo.DTOs.QuotationDTO;
import com.example.demo.Models.Quotation;
import com.example.demo.Models.Source;
import com.example.demo.Repositories.QuotationRepository;
import com.example.demo.Repositories.SourceRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@Controller
public class QuotationController {
    @Autowired
    QuotationRepository quotationRepository;

    @Autowired
    SourceRepository sourceRepository;

    @PostMapping(value = "/quotation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody QuotationDTO quotationDTO) {
        if(quotationDTO.getId_source() == 0)
            return new ResponseEntity<>(Collections.singletonList("Quotation should be have a source"), HttpStatus.BAD_REQUEST);;

        Quotation quotation = new Quotation();
        quotation.setContent(quotationDTO.getContent());

        // TODO: mover esse código para dentro do construtor (que será criado) de Quotation
        Source source = new Source();
        source.setId(quotationDTO.getId_source());
        quotation.setSource(source);

        Quotation quotationSaved = quotationRepository.save(quotation);

        return new ResponseEntity<>(quotationSaved, HttpStatus.CREATED);
    }

    @GetMapping(value="/quotation")
    public ResponseEntity<?> index(@RequestParam(required=false) Optional<Long> id_source, @RequestParam(required=false) Optional<String> content) {
        if(!id_source.isPresent() && !content.isPresent()) return new ResponseEntity<>(quotationRepository.findAll(), HttpStatus.OK);

        if(id_source.isPresent() && !content.isPresent()) {
            Optional<Source> foundSource = sourceRepository.findById(id_source.get());

            if (!foundSource.isPresent())
                return new ResponseEntity<>(Collections.singletonList("Source not found"), HttpStatus.NOT_FOUND);

            Source source = foundSource.get();
            return new ResponseEntity<>(source.getQuotations(), HttpStatus.OK);
        }

        if(!id_source.isPresent()) {
            Iterable<Quotation> quotations = quotationRepository.findByContentLike(content.get());
            return new ResponseEntity<>(quotations, HttpStatus.OK);
        }

        return new ResponseEntity<>(quotationRepository.findBySourceAndContent(id_source.get(), content.get()), HttpStatus.OK);
    }

    @PutMapping(value = "/quotation/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody QuotationDTO quotationDTO, @PathVariable(name = "id") long id) {
        Optional<Quotation> foundQuotation = quotationRepository.findById(id);

        if(!foundQuotation.isPresent())
            return new ResponseEntity<>(Collections.singletonList(String.format("Quotation with id '%d' is not found", id)),HttpStatus.NOT_FOUND);

        Quotation quotation = foundQuotation.get();
        quotation.setContent(quotationDTO.getContent());

        // TODO: mover esse código para dentro do construtor (que será criado) de Quotation
        Source source = new Source();
        source.setId(quotationDTO.getId_source());
        quotation.setSource(source);

        Quotation quotationSaved = quotationRepository.save(quotation);

        return new ResponseEntity<>(quotationSaved, HttpStatus.OK);
    }

    @DeleteMapping("/quotation/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        quotationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
