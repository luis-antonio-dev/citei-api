package com.example.demo.Controllers;

import com.example.demo.Models.Source;
import com.example.demo.Repositories.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
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
}
