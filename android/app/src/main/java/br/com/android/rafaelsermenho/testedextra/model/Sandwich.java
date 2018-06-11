package br.com.android.rafaelsermenho.testedextra.model;

import java.util.List;

import br.com.android.rafaelsermenho.testedextra.presenter.implementation.Light;
import br.com.android.rafaelsermenho.testedextra.presenter.implementation.MuitaCarne;
import br.com.android.rafaelsermenho.testedextra.presenter.implementation.MuitoQueijo;
import br.com.android.rafaelsermenho.testedextra.presenter.interfaces.IPromotion;

public class Sandwich {
    private int id;
    private String name;
    private String image;
    private String ingredientsDescription;
    private float sandwichPrice;
    private boolean custom;
    private List<Ingredient> ingredientList;

    public Sandwich(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> newIngredients) {
        if (this.ingredientList == null) {
            this.ingredientList = newIngredients;
        } else {
            this.ingredientList.addAll(newIngredients);
        }
        updateSandwichIngredients(ingredientList);
        updateSandwichPrice(ingredientList);
    }

    private void updateSandwichIngredients(List<Ingredient> ingredients) {
        ingredientsDescription = "";
        for (int i = 0; i < ingredients.size(); i++) {
            if (i < ingredients.size() - 1) {
                ingredientsDescription = ingredientsDescription + ingredients.get(i).getName() + ", ";
            } else {
                ingredientsDescription = ingredientsDescription + ingredients.get(i).getName();
            }
        }
        setIngredientsDescription(ingredientsDescription);
    }

    private void updateSandwichPrice(List<Ingredient> ingredients) {
        sandwichPrice = 0;
        for (int i = 0; i < ingredients.size(); i++) {
            sandwichPrice = sandwichPrice + ingredients.get(i).getPrice();
        }
        IPromotion light = new Light();
        IPromotion muitaCarne = new MuitaCarne();
        IPromotion muitoQueijo = new MuitoQueijo();
        light.applyDiscount(this);
        muitaCarne.applyDiscount(this);
        muitoQueijo.applyDiscount(this);
        setSandwichPrice(sandwichPrice);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private void setIngredientsDescription(String ingredientsDescription) {
        this.ingredientsDescription = ingredientsDescription;
    }

    public void setSandwichPrice(float sandwichPrice) {
        this.sandwichPrice = sandwichPrice;
    }

    public String getIngredientDescription() {
        return ingredientsDescription;
    }

    public float getSandwichPrice() {
        return sandwichPrice;
    }

    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    public boolean isCustom() {
        return custom;
    }

    public String getIngredientsDescription() {
        return ingredientsDescription;
    }
}