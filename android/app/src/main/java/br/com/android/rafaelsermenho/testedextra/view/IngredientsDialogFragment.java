package br.com.android.rafaelsermenho.testedextra.view;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.android.rafaelsermenho.testedextra.R;
import br.com.android.rafaelsermenho.testedextra.adapter.IQuantityChangeListener;
import br.com.android.rafaelsermenho.testedextra.adapter.IngredientsAdapter;
import br.com.android.rafaelsermenho.testedextra.presenter.implementation.IngredientsDialogPresenter;
import br.com.android.rafaelsermenho.testedextra.model.Ingredient;

public class IngredientsDialogFragment extends DialogFragment {

    private List<Ingredient> ingredientList;
    private IngredientsDialogPresenter presenter;
    private IngredientsDialogListener mListener;

    public interface IngredientsDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, ArrayList<Ingredient> newIngredients);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (IngredientsDialogListener) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_ingredients, null);
        builder.setView(view)
                .setPositiveButton("pronto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(IngredientsDialogFragment.this, presenter.getNewIngredients());
                    }
                });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ingredientsRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        ingredientList = getArguments().getParcelableArrayList("ingredients");

        IQuantityChangeListener listener = new IQuantityChangeListener() {
            @Override
            public void onQuantityChanged(int quantity, int position) {
                Ingredient ingredient = ingredientList.get(position);
                for (int i = 1; i <= quantity; i++) {
                    presenter.addIngredient(ingredient);
                }
                if (quantity == 0) {
                    presenter.removeIngredient(ingredient);
                }
            }
        };

        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(view.getContext(), ingredientList, listener);
        recyclerView.setAdapter(ingredientsAdapter);

        presenter = new IngredientsDialogPresenter();

        return builder.create();
    }
}
