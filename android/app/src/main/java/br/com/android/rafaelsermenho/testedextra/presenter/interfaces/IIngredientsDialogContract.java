package br.com.android.rafaelsermenho.testedextra.presenter.interfaces;

import java.util.ArrayList;

import br.com.android.rafaelsermenho.testedextra.model.Ingredient;

public interface IIngredientsDialogContract {
    interface Presenter {
        ArrayList<Ingredient> getNewIngredients();

        void addIngredient(Ingredient ingredient);

        void removeIngredient(Ingredient ingredient);
    }

}
