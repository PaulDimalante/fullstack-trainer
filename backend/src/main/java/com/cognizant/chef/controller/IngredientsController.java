package com.cognizant.chef.controller;

import com.cognizant.chef.model.Ingredient;
import com.cognizant.chef.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {
    @Autowired
    public IngredientService ingredientService;

    @PostMapping("/save")
    public ResponseEntity<Ingredient> save(@RequestBody Ingredient ingredient) {
        Ingredient response = this.ingredientService.save(ingredient);
        HttpStatus status;
        if(response == null) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.OK;
        }
        ResponseEntity<Ingredient> responseEntity = new ResponseEntity<Ingredient>(response, status);
        return responseEntity;
    }

    @PostMapping("/delete")
    public ResponseEntity<Ingredient> delete(@RequestBody Ingredient ingredient) {
        Ingredient nullIngredient = null;
        boolean success = this.ingredientService.delete(ingredient);
        HttpStatus status;
        if(success) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        ResponseEntity<Ingredient> responseEntity = new ResponseEntity<Ingredient>(nullIngredient, status);
        return responseEntity;
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Ingredient> findById(@PathVariable Long id) {
        Ingredient response = this.ingredientService.findById(id);
        HttpStatus status;
        if(response == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }
        ResponseEntity<Ingredient> responseEntity = new ResponseEntity<Ingredient>(response, status);
        return responseEntity;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Ingredient>> findAll() {
        List<Ingredient> response = this.ingredientService.findAll();
        HttpStatus status;
        if(response == null) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.OK;
        }
        ResponseEntity<List<Ingredient>> responseEntity = new ResponseEntity<List<Ingredient>>(response, status);
        return responseEntity;
    }
}
