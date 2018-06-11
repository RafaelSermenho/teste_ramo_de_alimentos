package br.com.android.rafaelsermenho.testedextra.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import br.com.android.rafaelsermenho.testedextra.R;
import br.com.android.rafaelsermenho.testedextra.adapter.OrderAdapter;
import br.com.android.rafaelsermenho.testedextra.presenter.implementation.OrderPresenter;
import br.com.android.rafaelsermenho.testedextra.presenter.interfaces.IOrderContract;
import br.com.android.rafaelsermenho.testedextra.model.Order;

public class OrderActivity extends AppCompatActivity implements IOrderContract.View {

    private RecyclerView recyclerView;
    private TextView txtNoOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        OrderPresenter presenter = new OrderPresenter(this);
        presenter.getOrderInfo();

        recyclerView = (RecyclerView) findViewById(R.id.orderRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        txtNoOrder = (TextView) findViewById(R.id.txtNoPromotion);
    }

    @Override
    public void onGetDataSuccess(List<Order> orderList) {
        OrderAdapter orderAdapter = new OrderAdapter(OrderActivity.this, orderList);
        recyclerView.setAdapter(orderAdapter);
        recyclerView.setVisibility(View.VISIBLE);
        txtNoOrder.setVisibility(View.GONE);
    }

    @Override
    public void onNoDataReturned() {
        recyclerView.setVisibility(View.GONE);
        txtNoOrder.setVisibility(View.VISIBLE);
    }
}
