package com.example.mealmatchapp.ui.dashboard;

public class FilteredIngredient {
    public String name;
    public boolean include;

    FilteredIngredient(String name, boolean included) {
        this.name = name;
        this.include = included;
    }
}
