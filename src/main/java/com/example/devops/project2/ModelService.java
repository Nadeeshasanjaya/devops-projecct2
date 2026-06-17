package com.example.devops.project2;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    List<Model> models = new ArrayList<>();

    public List<Model> getAllModels() {
         System.out.println("API called");
        return models;
    }

    public void add(Model model) {
        models.add(model);
    }

    public void update(int id, Model model) {
        for (int i = 0; i < models.size(); i++) {
            if (models.get(i).getId() == id) {
                models.set(i, model);
                return;
            }
        }
    }

    public void delete(int id) {
        for (int i = 0; i < models.size(); i++) {
            if (models.get(i).getId() == id) {
                models.remove(i);
                return;
            }
        }


        
    }
}