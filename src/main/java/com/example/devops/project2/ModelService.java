package com.example.devops.project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {

    @Autowired
    private ModelRepository repository;

    public List<Model> getAllModels() {
        return repository.findAll();
    }

    public void add(Model model) {
        repository.save(model);
    }

    public void update(int id, Model model) {
        model.setId(id);
        repository.save(model);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}