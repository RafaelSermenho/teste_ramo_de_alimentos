package br.com.android.rafaelsermenho.testedextra.presenter.interfaces;

import java.util.List;

import br.com.android.rafaelsermenho.testedextra.model.Order;

public interface IOrderContract {
    interface Presenter {
        void getOrderInfo();
    }

    interface View {

        void onGetDataSuccess(List<Order> orderList);


        void onNoDataReturned();
    }
}
