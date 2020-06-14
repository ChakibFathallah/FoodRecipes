
package com.mvvm.foodrecipes;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mvvm.foodrecipes.models.Recipe;
import com.mvvm.foodrecipes.requests.RestApi;
import com.mvvm.foodrecipes.requests.RestService;
import com.mvvm.foodrecipes.requests.responses.RecipeResponse;
import com.mvvm.foodrecipes.requests.responses.RecipeSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends BaseActivity {

    private static final String TAG = "RecipeListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofitRequest();
            }
        });

    }


    private void testRetrofitRequest(){
       /* RestApi restApi = RestService.getRestApi();

        Call<RecipeSearchResponse> responseCall = restApi.searchRecipe(
                "chicken breast",
                "1"
        );

        responseCall.enqueue(new Callback<RecipeSearchResponse>() {
            @Override
            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
                Log.d(TAG, "onResponse: server response" + response.toString());
                if (response.code() == 200){
                    Log.d(TAG,"onResponse: " + response.body().toString());
                    List<Recipe> recipeList = new ArrayList<>(response.body().getRecipes());
                    for (Recipe recipe : recipeList){
                        Log.d(TAG,"onResponse: recipe title =>" + recipe.getTitle());
                    }
                } else {
                    try {
                        Log.d(TAG,"onResponse: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {

            }
        });*/


        RestApi restApi = RestService.getRestApi();

        Call<RecipeResponse> responseCall = restApi.getRecipe(
                "41470"
        );

        responseCall.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                Log.d(TAG, "onResponse: server response => " + response.toString());
                if (response.code() == 200){
                    Log.d(TAG,"onResponse: retrieve recipe => " + response.body().toString());
                    Recipe recipe = (response.body().getRecipe());
                } else {
                    try {
                        Log.e(TAG,"onResponse: error =>" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {

            }
        });
    }
}
