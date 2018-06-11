package br.com.android.rafaelsermenho.testedextra.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import br.com.android.rafaelsermenho.testedextra.R;
import br.com.android.rafaelsermenho.testedextra.adapter.IClickListener;
import br.com.android.rafaelsermenho.testedextra.adapter.SandwichAdapter;
import br.com.android.rafaelsermenho.testedextra.presenter.implementation.SandwichListPresenter;
import br.com.android.rafaelsermenho.testedextra.presenter.interfaces.ISandwichListContract;
import br.com.android.rafaelsermenho.testedextra.model.Sandwich;

public class SandwichListActivity extends AppCompatActivity implements ISandwichListContract.View {

    private RecyclerView recyclerView;
    private SandwichAdapter sandwichAdapter;
    private IClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sandwich_list_activity);

        SandwichListPresenter presenter = new SandwichListPresenter(this);
        presenter.getSandwichAndIngredients();

        recyclerView = (RecyclerView) findViewById(R.id.sandwichListRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        listener = new IClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent startEditSandwichActivity = new Intent(getApplicationContext(), EditSandwichActivity.class);
                startEditSandwichActivity.putExtra("LANCHE_ID", (int) sandwichAdapter.getItemId(position));
                startActivity(startEditSandwichActivity);
            }
        };

    }

    @Override
    public void onGetDataSuccess(List<Sandwich> sandwichList) {
        sandwichAdapter = new SandwichAdapter(getApplicationContext(), sandwichList, listener);
        recyclerView.setAdapter(sandwichAdapter);
    }


}
