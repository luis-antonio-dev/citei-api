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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
