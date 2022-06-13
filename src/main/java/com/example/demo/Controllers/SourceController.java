package com.example.demo.Controllers;

import com.example.demo.Models.Source;
import com.example.demo.Repositories.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SourceController {
    @Autowired
    SourceRepository sourceRepository;

    @PostMapping("/source")
    public ResponseEntity<Source> create(Source sourceModel){
        sourceRepository.save(sourceModel);
        return new ResponseEntity<>(sourceModel, HttpStatus.OK);
    }


}
