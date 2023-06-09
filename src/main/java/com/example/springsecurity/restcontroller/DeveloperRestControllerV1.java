package com.example.springsecurity.restcontroller;

import com.example.springsecurity.model.Developer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAuthority('developers:read')")
    public Developer getById(@PathVariable Long id){
        return DEVELOPERS.stream().filter(
                dev -> dev.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('developers:write')")
    public Developer create(@RequestBody Developer developer){
        this.DEVELOPERS.add(developer);
        return developer;
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public void deleteById(@PathVariable Long id){
        this.DEVELOPERS.removeIf(dev -> dev.getId().equals(id));
    }
}
