package br.com.android.rafaelsermenho.testedextra.view;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.android.rafaelsermenho.testedextra.R;
import br.com.android.rafaelsermenho.testedextra.presenter.implementation.EditSandwichPresenter;
import br.com.android.rafaelsermenho.testedextra.presenter.interfaces.IEditSandwichContract;
import br.com.android.rafaelsermenho.testedextra.model.Ingredient;

public class EditSandwichActivity extends AppCompatActivity implements IEditSandwichContract.View, IngredientsDialogFragment.IngredientsDialogListener {

    private EditSandwichPresenter presenter;
    private TextView txtNomeLanche;
    private TextView txtPrecoLanche;
    private TextView txtIngredientesLanche;
    private ImageView imgFotoLanche;
    private int lanche_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_montar_lanche);

        txtNomeLanche = (TextView) findViewById(R.id.nomeLanche);
        txtIngredientesLanche = (TextView) findViewById(R.id.ingredientesLanche);
        txtPrecoLanche = (TextView) findViewById(R.id.precoLanche);
        imgFotoLanche = (ImageView) findViewById(R.id.fotoIngrediente);
        Button btnCarrinho = (Button) findViewById(R.id.btnCarrinho);
        btnCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addSandwich(lanche_id);
            }
        });
        Button btnCustomizar = (Button) findViewById(R.id.btnCustomizar);
        btnCustomizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getAllIngredients();
            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra("LANCHE_ID")) {
            lanche_id = intent.getIntExtra("LANCHE_ID", 0);
            presenter = new EditSandwichPresenter(this);
            presenter.getSandwich(lanche_id);
        }
    }


    @Override
    public void setSandwichName(String name) {
        txtNomeLanche.setText(name);
    }

    @Override
    public void setSandwichPrice(float precoLanche) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        txtPrecoLanche.setText(format.format(precoLanche));
    }

    @Override
    public void setIngredientsDescription(String descricaoIngredientes) {
        txtIngredientesLanche.setText(descricaoIngredientes);
    }

    @Override
    public void setSandwichImage(String image) {
        Glide.with(this).load(image).into(imgFotoLanche);
    }

    @Override
    public void returnToListScreen() {
        onBackPressed();
    }

    @Override
    public void onGetDataSuccess(List<Ingredient> ingredientList) {
        showIngredientsDialog(ingredientList);
    }

    private void showIngredientsDialog(List<Ingredient> ingredientList) {
        IngredientsDialogFragment ingredientsDialogFragment = new IngredientsDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ingredients", (ArrayList<? extends Parcelable>) ingredientList);
        ingredientsDialogFragment.setArguments(bundle);
        ingredientsDialogFragment.show(getFragmentManager(), "ingredients");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, ArrayList<Ingredient> newIngredients) {
        presenter.addNewIngredients(newIngredients);
    }
}
