
package com.mvvm.foodrecipes;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mvvm.foodrecipes.adapters.OnRecipeListener;
import com.mvvm.foodrecipes.adapters.RecipeAdapter;
import com.mvvm.foodrecipes.models.Recipe;
import com.mvvm.foodrecipes.requests.RestApi;
import com.mvvm.foodrecipes.requests.RestService;
import com.mvvm.foodrecipes.requests.responses.RecipeResponse;
import com.mvvm.foodrecipes.viewmodels.RecipeListViewModel;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends BaseActivity implements OnRecipeListener {

    private static final String TAG = RecipeListActivity.class.getSimpleName();

    private RecipeListViewModel mRecipeListViewModel;
    private RecyclerView mRecyclerView;
    private RecipeAdapter mRecipeAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        mRecyclerView = findViewById(R.id.recipe_list);

        mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);

        initRecyclerView();
        subscribeObservers();
        initSearchView();
    }

    private void subscribeObservers(){
        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                if (recipes != null){
                    for (Recipe recipe : recipes){
                        Log.d(TAG, "onChanged: " + recipe.getTitle());
                    }
                    mRecipeAdapter.setRecipes(recipes);
                }
            }
        });

        //mRecipeListViewModel.is
    }

    private void initRecyclerView(){
        mRecipeAdapter = new RecipeAdapter(this);
        mRecyclerView.setAdapter(mRecipeAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
    }

    private void initSearchView(){
        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mRecipeAdapter.displayLoading();
                mRecipeListViewModel.searchRecipesApi(query, 1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onRecipeClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {

    }

    private void testRetrofitGetRecipeRequest(){
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
