package br.com.android.rafaelsermenho.testedextra.presenter.interfaces;

import java.util.List;

import br.com.android.rafaelsermenho.testedextra.model.Promotion;

public interface IPromotionContract {
    interface Presenter {
        void getPromotions();
    }

    interface View {

        void onGetDataSuccess(List<Promotion> promotionList);


        void onNoDataReturned();
    }
}
