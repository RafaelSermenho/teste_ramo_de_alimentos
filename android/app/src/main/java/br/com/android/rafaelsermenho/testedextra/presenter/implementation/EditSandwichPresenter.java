package br.com.android.rafaelsermenho.testedextra.presenter.implementation;


import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.android.rafaelsermenho.testedextra.presenter.interfaces.IEditSandwichContract;
import br.com.android.rafaelsermenho.testedextra.model.Ingredient;
import br.com.android.rafaelsermenho.testedextra.model.Sandwich;
import br.com.android.rafaelsermenho.testedextra.model.network.RetrofitInstance;
import br.com.android.rafaelsermenho.testedextra.model.network.RetrofitService;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSandwichPresenter implements IEditSandwichContract.Presenter {

    private final IEditSandwichContract.View view;
    private List<Ingredient> newIngredients;
    private Sandwich sandwich;

    public EditSandwichPresenter(IEditSandwichContract.View view) {
        this.view = view;
    }

    @Override
    public void getSandwich(int sandwichId) {
        EditSandwichAsyncTask editSandwichAsyncTask = new EditSandwichAsyncTask(view);
        editSandwichAsyncTask.execute(sandwichId);
    }

    @Override
    public void addSandwich(int sandwichId) {
        if (sandwich.isCustom()) {
            addCustomSandwich(sandwichId);
        } else {
            RetrofitService service = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
            Call<Sandwich> callAddSandwich = service.addLanche(sandwichId);
            callAddSandwich.enqueue(new Callback<Sandwich>() {
                @Override
                public void onResponse(Call<Sandwich> call, Response<Sandwich> response) {
                    view.returnToListScreen();
                }

                @Override
                public void onFailure(Call<Sandwich> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void addCustomSandwich(int sandwichId) {
        RetrofitService service = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        List<Integer> idsList = new ArrayList<>();
        JSONObject extras = null;
        for (Ingredient ingredient : newIngredients) {
            idsList.add(ingredient.getId());
        }
        try {
            extras = new JSONObject().put("extras", "" + new JSONArray(idsList) + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(extras));
        Call<Sandwich> callAddCustomSandwich = service.addCustomSandwich(sandwichId, body);
        callAddCustomSandwich.enqueue(new Callback<Sandwich>() {
            @Override
            public void onResponse(Call<Sandwich> call, Response<Sandwich> response) {
                view.returnToListScreen();
            }

            @Override
            public void onFailure(Call<Sandwich> call, Throwable t) {

            }
        });
    }

    @Override
    public void getAllIngredients() {
        RetrofitService service = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<Ingredient>> call = service.getAllIngredients();
        call.enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                view.onGetDataSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable t) {

            }
        });
    }

    @Override
    public void addNewIngredients(List<Ingredient> newIngredients) {
        this.newIngredients = newIngredients;
        sandwich.setCustom(true);
        sandwich.setIngredientList(newIngredients);
        sandwich.setName(sandwich.getName() + " - do seu jeito");
        updateSandwichData();
    }

    private void getSandwichById(int sandwichId) {
        RetrofitService service = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<Sandwich> callGetSandwich = service.getSandwich(sandwichId);
        sandwich = new Sandwich(1, "X-Bacon", "https://goo.gl/W9WdaF");
        try {
            sandwich = callGetSandwich.execute().body();
            List<Ingredient> ingredients = getIngredientsOf(sandwich.getId());
            sandwich.setIngredientList(ingredients);
            sandwich.setCustom(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Ingredient> getIngredientsOf(final int id) {
        RetrofitService service = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        List<Ingredient> ingredients = null;
        Call<List<Ingredient>> callGetIngredientsOf = service.getIngredientsOf(id);
        try {
            ingredients = callGetIngredientsOf.execute().body();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    public class EditSandwichAsyncTask extends AsyncTask<Integer, Void, Void> {
        private final IEditSandwichContract.View view;

        public EditSandwichAsyncTask(
                IEditSandwichContract.View view) {
            this.view = view;
        }

        @Override
        protected Void doInBackground(Integer... params) {
            getSandwichById(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            updateSandwichData();
        }

    }

    private void updateSandwichData() {
        view.setSandwichName(sandwich.getName());
        view.setSandwichPrice(sandwich.getSandwichPrice());
        view.setIngredientsDescription(sandwich.getIngredientDescription());
        view.setSandwichImage(sandwich.getImage());
    }
}
