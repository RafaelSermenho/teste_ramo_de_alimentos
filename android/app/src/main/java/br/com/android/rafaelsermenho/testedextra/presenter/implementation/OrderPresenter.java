package br.com.android.rafaelsermenho.testedextra.presenter.implementation;


import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.android.rafaelsermenho.testedextra.model.Ingredient;
import br.com.android.rafaelsermenho.testedextra.model.Order;
import br.com.android.rafaelsermenho.testedextra.model.Sandwich;
import br.com.android.rafaelsermenho.testedextra.model.network.RetrofitInstance;
import br.com.android.rafaelsermenho.testedextra.model.network.RetrofitService;
import br.com.android.rafaelsermenho.testedextra.presenter.interfaces.IOrderContract;
import retrofit2.Call;

public class OrderPresenter implements IOrderContract.Presenter {

    private final IOrderContract.View view;

    public OrderPresenter(IOrderContract.View view) {
        this.view = view;
    }

    @Override
    public void getOrderInfo() {
        OrderAsyncTask orderAsyncTask = new OrderAsyncTask(view);
        orderAsyncTask.execute();
    }

    public class OrderAsyncTask extends AsyncTask<Object, Object, List<Order>> {
        private final IOrderContract.View view;

        public OrderAsyncTask(
                IOrderContract.View view) {
            this.view = view;
        }

        @Override
        protected List<Order> doInBackground(Object... params) {
            return getOrder();
        }

        @Override
        protected void onPostExecute(List<Order> order) {
            super.onPostExecute(order);
            if (order.isEmpty()) {
                view.onNoDataReturned();
            } else {
                view.onGetDataSuccess(order);
            }
        }
    }

    private List<Order> getOrder() {
        RetrofitService service = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<Order>> call = service.getOrder();
        List<Order> orderList = null;
        try {
            orderList = call.execute().body();

            for (final Order order : orderList) {
                Sandwich sandwich = getSandwich(order.getId_sandwich());

                List<Ingredient> ingredients = getIngredientsOf(order.getId_sandwich());
                sandwich.setIngredientList(ingredients);

                if (order.getExtras().length > 0) {
                    List<Ingredient> extraIngredients = getExtraIngredients(order.getExtras());
                    sandwich.setIngredientList(extraIngredients);
                    sandwich.setName(sandwich.getName() + " - do seu jeito");
                }
                order.setSandwich(sandwich);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    private List<Ingredient> getExtraIngredients(final String[] extras) {
        RetrofitService service = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<Ingredient>> call = service.getAllIngredients();
        final List<Ingredient> extraIngredients = new ArrayList<>();
        List<Ingredient> allIngredients;
        try {
            allIngredients = call.execute().body();
            for (String id : extras) {
                for (Ingredient ingredient : allIngredients) {
                    if (Integer.valueOf(id) == ingredient.getId()) {
                        extraIngredients.add(ingredient);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return extraIngredients;
    }

    private Sandwich getSandwich(int id_sandwich) {
        RetrofitService service = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<Sandwich> callSandwich = service.getSandwich(id_sandwich);
        Sandwich sandwich = new Sandwich(1, "X-Bacon", "https://goo.gl/W9WdaF");
        try {
            sandwich = callSandwich.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sandwich;

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


}
