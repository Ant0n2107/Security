package com.example.springsecurity.controller;

import com.example.springsecurity.model.Developer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRestControllerV1 {
    private final List<Developer> DEVELOPERS = Stream.of(
            new Developer(1L, "Tommy", "Brighton"),
            new Developer(2L, "Anthony", "Higgins"),
            new Developer(3L, "Jahsey", "Onfroy")
    ).collect(Collectors.toList());
    @GetMapping
    public List<Developer> getAll(){
        return DEVELOPERS;
    }
    @GetMapping("/{id}")
    public Developer getById(@PathVariable Long id){
        return DEVELOPERS.stream().filter(
                dev -> dev.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
