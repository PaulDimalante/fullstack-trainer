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

//    @Autowired
//    public IngredientService(IngredientRepository repository) {
//        this.repository = repository;
//    }

    public boolean login() {
        return true;
    }
    public Ingredient save(Ingredient ingredient) {
        return repository.save(ingredient);
    }
    public Ingredient delete(Ingredient ingredient) {
        return ingredient;
    }
    public List<Ingredient> findAll() {
        return this.repository.findAll();
    }
    public void deleteAll() {
    }
}
