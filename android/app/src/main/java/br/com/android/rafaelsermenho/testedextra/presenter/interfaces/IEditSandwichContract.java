package br.com.android.rafaelsermenho.testedextra.presenter.interfaces;

import java.util.List;

import br.com.android.rafaelsermenho.testedextra.model.Ingredient;

public interface IEditSandwichContract {
    interface Presenter {
        void getSandwich(int lancheId);

        void addSandwich(int lanche_id);

        void addCustomSandwich(int lanche_id);

        void getAllIngredients();

        void addNewIngredients(List<Ingredient> ingredients);
    }

    interface View {

        void setSandwichName(String name);

        void setSandwichPrice(float precoLanche);

        void setIngredientsDescription(String descricaoIngredientes);

        void setSandwichImage(String image);

        void returnToListScreen();

        void onGetDataSuccess(List<Ingredient> ingredientList);
    }
}
