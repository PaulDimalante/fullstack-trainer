package com.cognizant.chef.service;

import com.cognizant.chef.model.Ingredient;
import com.cognizant.chef.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IngredientService {
    @Autowired
    public IngredientRepository repository;

    public boolean login() {
        return true;
    }
    public Ingredient save(Ingredient ingredient) {
        return repository.save(ingredient);
    }
    public boolean delete(Ingredient ingredient) {
        boolean exists = this.existsById(ingredient.getId());
        if(exists) this.repository.delete(ingredient);
        return exists;
    }
    public boolean existsById(Long id) {
        return this.repository.existsById(id);
    }
    public Ingredient findById(Long id) {
        return this.repository.findById(id).orElse(null);
    }
    public List<Ingredient> findAll() {
        return this.repository.findAll();
    }
//    public void deleteAll() {    }
}
