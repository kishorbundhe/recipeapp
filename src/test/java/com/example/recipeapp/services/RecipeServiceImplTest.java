package com.example.recipeapp.services;

import com.example.recipeapp.domain.Recipe;
import com.example.recipeapp.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {
    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipeServiceImpl recipeService;

    @Test
    void getRecipes() {
            Recipe recipe = new Recipe();
            Set<Recipe> recipeData=new HashSet<>();
            recipeData.add(recipe);

            Mockito.when(recipeRepository.findAll()).thenReturn(recipeData);
            recipeService.getRecipes();
            assertThat(1,equalTo(recipeData.size()));
            Mockito.verify(recipeRepository,Mockito.times(1)).findAll();
    }


}