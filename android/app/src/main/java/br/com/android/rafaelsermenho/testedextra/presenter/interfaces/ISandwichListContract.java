package br.com.android.rafaelsermenho.testedextra.presenter.interfaces;

import java.util.List;

import br.com.android.rafaelsermenho.testedextra.model.Sandwich;

public interface ISandwichListContract {
    interface Presenter {
        void getSandwichAndIngredients();
    }

    interface View {

        void onGetDataSuccess(List<Sandwich> sandwichList);
    }
}
