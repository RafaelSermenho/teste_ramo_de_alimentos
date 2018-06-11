package br.com.android.rafaelsermenho.testedextra.model;

import java.util.List;

public class Order {
    private int id;

    private int id_sandwich;

    private String date;

    private String[] extras;
    private List<Ingredient> ingredients;
    private String sandwichName;
    private Sandwich sandwich;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_sandwich() {
        return id_sandwich;
    }

    public String[] getExtras() {
        return extras;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setSandwich(Sandwich sandwich) {
        this.sandwich = sandwich;
    }

    public Sandwich getSandwich() {
        return sandwich;
    }
}

