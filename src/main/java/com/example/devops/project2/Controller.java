package com.example.devops.project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private ModelService service;

    
    @GetMapping("/models")
    public List<Model> getAllModels() {
        return service.getAllModels();
    }

    @PostMapping("/models")
    public void addModel(@RequestBody Model model) {
        service.add(model);
    }

    @PutMapping("/models/{id}")
    public void updateModel(@PathVariable int id, @RequestBody Model model) {
        service.update(id, model);
    }

    @DeleteMapping("/models/{id}")
    public void deleteModel(@PathVariable int id) {
        service.delete(id);
    }
}