package com.mvvm.foodrecipes.adapters;

public interface OnRecipeListener {

    void onRecipeClick(int position);

    void onCategoryClick(String category);
}
