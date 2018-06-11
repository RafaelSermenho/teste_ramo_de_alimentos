package br.com.android.rafaelsermenho.testedextra.model.network;

import java.util.List;

import br.com.android.rafaelsermenho.testedextra.model.Ingredient;
import br.com.android.rafaelsermenho.testedextra.model.Order;
import br.com.android.rafaelsermenho.testedextra.model.Promotion;
import br.com.android.rafaelsermenho.testedextra.model.Sandwich;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitService {
    @GET("lanche")
    Call<List<Sandwich>> getSandwiches();

    @GET("ingrediente/de/{lanche}")
    Call<List<Ingredient>> getIngredientsOf(@Path("lanche") Integer id);

    @GET("lanche/{lanche}")
    Call<Sandwich> getSandwich(@Path("lanche") Integer id);

    @PUT("pedido/{lanche}")
    Call<Sandwich> addLanche(@Path("lanche") Integer id);

    @PUT("pedido/{lanche}")
    Call<Sandwich> addCustomSandwich(@Path("lanche") Integer id, @Body RequestBody extras);

    @GET("ingrediente")
    Call<List<Ingredient>> getAllIngredients();

    @GET("pedido")
    Call<List<Order>> getOrder();

    @GET("promocao")
    Call<List<Promotion>> getPromotions();
}