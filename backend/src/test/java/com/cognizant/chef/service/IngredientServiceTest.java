package com.cognizant.chef.service;

import com.cognizant.chef.model.Ingredient;
import com.cognizant.chef.repository.IngredientRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceTest {
    @Mock
    public IngredientRepository repository;

    @InjectMocks
    private IngredientService service;

    @Before
    public void before() {
 //       this.service = new IngredientService();
        this.service.deleteAll();
    }

    @After
    public void after() {
        this.service.deleteAll();
    }

    @Test
    public void loginShouldReturnTrueIfSucessfull() {
        boolean result = this.service.login();
        assertEquals(true, result);
    }

    @Test
    public void saveOfNewIngredientShouldReturnIngredientSaved() {
        Ingredient ingredient = new Ingredient("hot sauce");
        when(this.repository.save(any(Ingredient.class))).thenReturn(ingredient);
        Ingredient result = this.service.save(ingredient);
        assertEquals(ingredient, result);
    }

    @Test
    public void deleteShouldReturnIngredientDeleted() {
        Ingredient ingredient = new Ingredient("hot sauce");
        when(this.repository.save(any(Ingredient.class))).thenReturn(ingredient);
        Ingredient ingredientSaved = this.service.save(ingredient);
        Ingredient result = this.service.delete(ingredientSaved);
        assertEquals(ingredientSaved, result);
    }

    @Test
    public void  saveOfExistingIngredientShouldReturnIngredientSaved() {
        Ingredient ingredient = new Ingredient("hot sauce");
        when(this.repository.save(any(Ingredient.class))).thenReturn(ingredient);
        Ingredient ingredientSaved = this.service.save(ingredient);
        ingredientSaved.setName("tobasco sauce");
        when(this.repository.save(any(Ingredient.class))).thenReturn(ingredientSaved);
        Ingredient result = this.service.save(ingredientSaved);
        assertEquals(ingredientSaved, result);
    }

    @Test
    public void  findAllShouldReturnListOfAllIngredients() {
        Ingredient ingredient = new Ingredient("hot sauce");
        when(this.repository.save(any(Ingredient.class))).thenReturn(ingredient);
        Ingredient ingredientSaved = this.service.save(ingredient);
        List<Ingredient> expected = new ArrayList<Ingredient>();
        expected.add(ingredientSaved);
        when(this.repository.findAll()).thenReturn(expected);
        List<Ingredient> result = this.service.findAll();
        assertEquals(expected, result);
    }
}
