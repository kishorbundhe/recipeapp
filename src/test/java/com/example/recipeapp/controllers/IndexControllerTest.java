package com.example.recipeapp.controllers;

import com.example.recipeapp.domain.Recipe;
import com.example.recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;

import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IndexControllerTest {
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    IndexController controller;


    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new IndexController(recipeService);
    }
    @Test
    public void testMockMVC() throws Exception{
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/")).
                andExpect(status().isOk()).
                andExpect(view().name("index"));
    }

    @Test
    void getIndexPage() {
        HashSet<Recipe> recipes = new HashSet<>();
        Recipe recipe= new Recipe();
        recipe.setId(1L);
        Recipe recipe1= new Recipe();
        recipe1.setId(2L);
        recipes.add(recipe);
        recipes.add(recipe1);
        when(recipeService.getRecipes()).thenReturn(recipes);

        String view = controller.getIndexPage(model);

        // Argument captor
        ArgumentCaptor<Set<Recipe>> captor = ArgumentCaptor.forClass(Set.class);

        assertEquals(view, "index");
        verify(recipeService, times(1)).getRecipes();
        verify(model,times(1)).addAttribute(eq("recipes"),captor.capture());
        Set<Recipe> captured = captor.getValue();
        assertEquals(captured,recipes);
    }


}