package br.com.android.rafaelsermenho.testedextra.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import br.com.android.rafaelsermenho.testedextra.R;
import br.com.android.rafaelsermenho.testedextra.adapter.PromotionAdapter;
import br.com.android.rafaelsermenho.testedextra.presenter.implementation.PromotionPresenter;
import br.com.android.rafaelsermenho.testedextra.presenter.interfaces.IPromotionContract;
import br.com.android.rafaelsermenho.testedextra.model.Promotion;

public class PromotionActivity extends AppCompatActivity implements IPromotionContract.View {


    private RecyclerView recyclerView;
    private TextView txtNoOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);

        PromotionPresenter presenter = new PromotionPresenter(this);
        presenter.getPromotions();

        recyclerView = (RecyclerView) findViewById(R.id.promotionRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        txtNoOrder = (TextView) findViewById(R.id.txtNoPromotion);
    }

    @Override
    public void onGetDataSuccess(List<Promotion> promotionList) {
        PromotionAdapter promotionAdapter = new PromotionAdapter(PromotionActivity.this, promotionList);
        recyclerView.setAdapter(promotionAdapter);
        txtNoOrder.setVisibility(View.GONE);
    }

    @Override
    public void onNoDataReturned() {
        recyclerView.setVisibility(View.GONE);
        txtNoOrder.setVisibility(View.VISIBLE);
    }
}
