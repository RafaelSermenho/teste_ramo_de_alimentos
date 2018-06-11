package br.com.android.rafaelsermenho.testedextra.presenter.implementation;

import java.util.ArrayList;
import java.util.List;

import br.com.android.rafaelsermenho.testedextra.presenter.interfaces.IPromotion;
import br.com.android.rafaelsermenho.testedextra.model.Ingredient;
import br.com.android.rafaelsermenho.testedextra.model.Sandwich;

public class Light implements IPromotion {
    private static final float PERCENTUAL_DESCONTO_LIGHT = 0.10f;
    private static final int ID_ALFACE = 1;
    private static final int ID_BACON = 2;

    @Override
    public void applyDiscount(Sandwich sandwich) {
        List<Ingredient> ingredients = sandwich.getIngredientList();
        List<Integer> idsIngredients = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            idsIngredients.add(ingredient.getId());
        }

        if (idsIngredients.contains(ID_ALFACE) && !idsIngredients.contains(ID_BACON)) {
            sandwich.setSandwichPrice(sandwich.getSandwichPrice() - (sandwich.getSandwichPrice() * PERCENTUAL_DESCONTO_LIGHT));
        }
    }
}
