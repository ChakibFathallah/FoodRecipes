package com.mvvm.foodrecipes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mvvm.foodrecipes.R;
import com.mvvm.foodrecipes.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int RECIPE_TYPE = 1;
    private static final int LOADING_TYPE = 2;
    private View view;
    private int itemViewType;

    private List<Recipe> mRecipes;
    private OnRecipeListener mOnRecipeListener;

    public RecipeAdapter(OnRecipeListener mOnRecipeListener) {
        this.mOnRecipeListener = mOnRecipeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = null;
        switch (viewType){
            case RECIPE_TYPE:{
                 view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recipe_list_item, parent, false);
                 return new RecipeViewHolder(view, mOnRecipeListener);
            }
            case LOADING_TYPE:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading_list_item, parent, false);
                return new LoadingViewHolder(view);
            }
            default:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recipe_list_item, parent, false);
                return new RecipeViewHolder(view, mOnRecipeListener);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mRecipes.get(position).getTitle().equals("LOADING...")){
            return LOADING_TYPE;
        } else {
            return RECIPE_TYPE;
        }
    }

    public void displayLoading(){
        if (!isLoading()){
            Recipe recipe = new Recipe();
            recipe.setTitle("LOADING...");
            List<Recipe> loadingRecipe = new ArrayList<>();
            loadingRecipe.add(recipe);
            mRecipes = loadingRecipe;
            notifyDataSetChanged();
        }

    }

    private boolean isLoading(){
        if (mRecipes != null){
            if(mRecipes.size() > 0){
                if (mRecipes.get(mRecipes.size() - 1).getTitle().equals("LOADING...")){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        itemViewType = getItemViewType(position);
        Recipe currentRecipe = mRecipes.get(position);

        if(itemViewType == RECIPE_TYPE){
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);
            Glide.with(holder.itemView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(currentRecipe.getImage_url())
                    .into(((RecipeViewHolder)holder).image);

            ((RecipeViewHolder)holder).title.setText(currentRecipe.getTitle());
            ((RecipeViewHolder)holder).publisher.setText(currentRecipe.getPublisher());
            ((RecipeViewHolder)holder).socialScore.setText(String.valueOf(Math.round(currentRecipe.getSocial_rank())));
        }



    }

    @Override
    public int getItemCount() {
        if (mRecipes != null)
        return mRecipes.size();
        return 0;
    }

    public void setRecipes(List<Recipe> recipes){
        mRecipes = recipes;
        notifyDataSetChanged();
    }
}
