package com.mvvm.foodrecipes.requests;

import com.mvvm.foodrecipes.requests.responses.RecipeResponse;
import com.mvvm.foodrecipes.requests.responses.RecipeSearchResponse;
import com.mvvm.foodrecipes.util.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RestApi {

    //SEARCH
    @GET(Constants.SEARCH)
    Call<RecipeSearchResponse> searchRecipe(
            @Query("q")  String query,
            @Query("page")  String page
    );

    //GET RECIPE REQUEST
    @GET(Constants.GET_RECIPE_BY_IC)
    Call<RecipeResponse> getRecipe(
            @Query("rId") String recipe_id
    );
}
