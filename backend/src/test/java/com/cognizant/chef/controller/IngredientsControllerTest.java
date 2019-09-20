package com.cognizant.chef.controller;

import com.cognizant.chef.model.Ingredient;
import com.cognizant.chef.service.IngredientService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class IngredientsControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private IngredientService service;

    private ObjectMapper objectMapper = new ObjectMapper();
    private Gson gson = new Gson();

    @Test
    public void findAllShouldReturnEmptyList() throws Exception {
        List<Ingredient> expected = new ArrayList<Ingredient>();
        when(service.findAll()).thenReturn(expected);
        String resultString = this.mvc.perform(get("/ingredients/findAll"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Ingredient> result = objectMapper.readValue(resultString, new TypeReference<List<Ingredient>>() {});
        assertEquals(expected, result);
    }

    @Test
    public void saveShouldReturnObjectSaved() throws Exception {
        Ingredient ingredient = new Ingredient(1L,"hot sauce");
        when(service.save(ingredient)).thenReturn(ingredient);
        String resultString = this.mvc.perform(post("/ingredients/save")
                .content(objectMapper.writeValueAsString(ingredient))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Ingredient result = objectMapper.readValue(resultString, Ingredient.class);
        assertEquals(ingredient, result);
    }

    @Test
    public void findByIdShouldReturnObjectWhenFound() throws Exception {
        Ingredient ingredient = new Ingredient(1L,"hot sauce");
        when(service.findById(ingredient.getId())).thenReturn(ingredient);
        String resultString = this.mvc.perform(get("/ingredients/findById/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Ingredient result = objectMapper.readValue(resultString, Ingredient.class);
        assertEquals(ingredient, result);
    }

    @Test
    public void findByIdShouldReturnNullObjectWhenNotFound() throws Exception {
        when(service.findById(1L)).thenReturn(null);
        String result = this.mvc.perform(get("/ingredients/findById/1"))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals("", result);
    }

    @Test
    public void deleteShouldReturnOkWhenFound() throws Exception {
        Ingredient ingredient = new Ingredient(1L, "hot sauce");
        when(service.delete(ingredient)).thenReturn(true);
        MockHttpServletResponse result = this.mvc.perform(post("/ingredients/delete")
                .content(objectMapper.writeValueAsString(ingredient))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.OK.value(), result.getStatus());
    }
}
