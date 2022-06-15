package com.example.demo.Controllers;

import com.example.demo.Models.Source;
import com.example.demo.Repositories.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class SourceController {
    @Autowired
    SourceRepository sourceRepository;

    @PostMapping("/source")
    @ResponseStatus(code=HttpStatus.OK)
    public ResponseEntity<?> create(@Valid Source sourceModel, BindingResult errors){
        if(errors.hasErrors())
            return new ResponseEntity<>(errors.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage), HttpStatus.BAD_REQUEST);

        sourceRepository.save(sourceModel);
        return new ResponseEntity<>(sourceModel, HttpStatus.OK);
    }

    @GetMapping("/source")
    public ResponseEntity<Iterable<Source>> list() {
        Iterable<Source> sources = sourceRepository.findAll();
        return new ResponseEntity<>(sources, HttpStatus.OK);
    }

    @GetMapping("/source/{id}")
    public ResponseEntity<?> show(@PathVariable(name = "id") long id) {
        Optional<Source> source = sourceRepository.findById(id);

        if(!source.isPresent()) return new ResponseEntity<>(Collections.singletonList("Source not found"), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(source, HttpStatus.OK);
    }

    @PutMapping("/source/{id}")
    public ResponseEntity<?> update(@Valid Source source, @PathVariable(name = "id") long id, BindingResult errors) {
        if(errors.hasErrors())
            return new ResponseEntity<>(errors.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage), HttpStatus.BAD_REQUEST);

        sourceRepository.save(source);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/source/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        sourceRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
