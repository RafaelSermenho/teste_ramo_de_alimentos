package br.com.android.rafaelsermenho.testedextra.presenter.implementation;


import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.android.rafaelsermenho.testedextra.presenter.interfaces.ISandwichListContract;
import br.com.android.rafaelsermenho.testedextra.model.Ingredient;
import br.com.android.rafaelsermenho.testedextra.model.Sandwich;
import br.com.android.rafaelsermenho.testedextra.model.network.RetrofitInstance;
import br.com.android.rafaelsermenho.testedextra.model.network.RetrofitService;
import retrofit2.Call;

public class SandwichListPresenter implements ISandwichListContract.Presenter {

    private final ISandwichListContract.View view;

    public SandwichListPresenter(ISandwichListContract.View view) {
        this.view = view;
    }

    @Override
    public void getSandwichAndIngredients() {
        SandwichAsyncTask sandwichAsyncTask = new SandwichAsyncTask(view);
        sandwichAsyncTask.execute();
    }

    private List<Sandwich> getSandwiches() {
        RetrofitService service = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        List<Sandwich> sandwichList = new ArrayList<>();
        Call<List<Sandwich>> call = service.getSandwiches();
        try {
            sandwichList = call.execute().body();
            for (final Sandwich sandwich : sandwichList) {
                List<Ingredient> ingredients = getIngredientsOf(sandwich.getId());
                sandwich.setIngredientList(ingredients);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sandwichList;
    }

    private List<Ingredient> getIngredientsOf(final int id) {
        RetrofitService service = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        List<Ingredient> ingredients = null;
        Call<List<Ingredient>> callGetIngredientsOfSandwich = service.getIngredientsOf(id);
        try {
            ingredients = callGetIngredientsOfSandwich.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    public class SandwichAsyncTask extends AsyncTask<Void, Void, List<Sandwich>> {
        private final ISandwichListContract.View view;

        public SandwichAsyncTask(
                ISandwichListContract.View view) {
            this.view = view;
        }

        @Override
        protected List<Sandwich> doInBackground(Void... params) {
            return getSandwiches();
        }

        @Override
        protected void onPostExecute(List<Sandwich> sandwiches) {
            super.onPostExecute(sandwiches);
            view.onGetDataSuccess(sandwiches);
        }
    }
}
