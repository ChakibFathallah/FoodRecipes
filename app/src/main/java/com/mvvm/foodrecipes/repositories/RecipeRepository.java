package com.mvvm.foodrecipes.repositories;

import androidx.lifecycle.LiveData;

import com.mvvm.foodrecipes.models.Recipe;
import com.mvvm.foodrecipes.requests.RecipeApiClient;

import java.util.List;

public class RecipeRepository {

    private static RecipeRepository instance;
    //private MutableLiveData<List<Recipe>> mRecipes;
    private RecipeApiClient mRecipeApiClient;


    public static RecipeRepository getInstance(){
        if (instance == null){
            instance = new RecipeRepository();
        }
        return instance;
    }

    private RecipeRepository(){
        mRecipeApiClient = RecipeApiClient.getInstance();
       // mRecipes = new MutableLiveData<>();
    }

    public LiveData<List<Recipe>> getRecipes(){
        return mRecipeApiClient.getRecipes();
    }

    public void searchRecipesApi(String query, int pageNumber){
        if (pageNumber == 0){
            pageNumber = 1;
        }
        mRecipeApiClient.searchRecipeApi(query, pageNumber);
    }
}
