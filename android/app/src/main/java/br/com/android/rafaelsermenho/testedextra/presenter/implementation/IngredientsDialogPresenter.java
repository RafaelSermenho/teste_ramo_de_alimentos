package br.com.android.rafaelsermenho.testedextra.presenter.implementation;


import java.util.ArrayList;

import br.com.android.rafaelsermenho.testedextra.presenter.interfaces.IIngredientsDialogContract;
import br.com.android.rafaelsermenho.testedextra.model.Ingredient;

public class IngredientsDialogPresenter implements IIngredientsDialogContract.Presenter {

    private final ArrayList<Ingredient> selected;

    public IngredientsDialogPresenter() {
        selected = new ArrayList<>();
    }

    @Override
    public ArrayList<Ingredient> getNewIngredients() {
        return selected;
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        selected.add(ingredient);
    }

    @Override
    public void removeIngredient(Ingredient ingredient) {
        if (selected.contains(ingredient)) {
            selected.remove(ingredient);
        }
    }
}
