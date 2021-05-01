package com.example.recipeapp.services;

import com.example.recipeapp.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public interface RecipeService {
    Set<Recipe> getRecipes();
}
