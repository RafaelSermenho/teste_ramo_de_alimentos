package br.com.android.rafaelsermenho.testedextra.presenter.implementation;


import java.util.List;

import br.com.android.rafaelsermenho.testedextra.presenter.interfaces.IPromotionContract;
import br.com.android.rafaelsermenho.testedextra.model.Promotion;
import br.com.android.rafaelsermenho.testedextra.model.network.RetrofitInstance;
import br.com.android.rafaelsermenho.testedextra.model.network.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PromotionPresenter implements IPromotionContract.Presenter {

    private final IPromotionContract.View view;

    public PromotionPresenter(IPromotionContract.View view) {
        this.view = view;
    }

    @Override
    public void getPromotions() {
        RetrofitService service = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<Promotion>> callGetPromotions = service.getPromotions();

        callGetPromotions.enqueue(new Callback<List<Promotion>>() {
            @Override
            public void onResponse(Call<List<Promotion>> call, Response<List<Promotion>> response) {
                List<Promotion> promotionList = response.body();
                if (promotionList.isEmpty()) {
                    view.onNoDataReturned();
                } else {
                    view.onGetDataSuccess(promotionList);
                }
            }

            @Override
            public void onFailure(Call<List<Promotion>> call, Throwable t) {

            }
        });
    }


}
