package br.com.android.rafaelsermenho.testedextra.presenter.implementation;

import java.util.List;

import br.com.android.rafaelsermenho.testedextra.presenter.interfaces.IPromotion;
import br.com.android.rafaelsermenho.testedextra.model.Ingredient;
import br.com.android.rafaelsermenho.testedextra.model.Sandwich;

public class MuitaCarne implements IPromotion {
    private static final float QTDE_LIMITE = 3;
    private static final float QTDE_COBRADA = 2;
    private static final int ID_HAMBURGUER = 3;
    private static final float PRECO_CARNE = 3.00f;

    @Override
    public void applyDiscount(Sandwich sandwich) {
        List<Ingredient> ingredients = sandwich.getIngredientList();
        int qtde = 0;
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getId() == ID_HAMBURGUER) {
                qtde++;
            }
        }
        int mod = qtde % 3;
        if (mod == 0 && qtde > 0) {
            sandwich.setSandwichPrice(sandwich.getSandwichPrice() + qtde - (QTDE_COBRADA * qtde / QTDE_LIMITE) * PRECO_CARNE);
        }
    }
}
