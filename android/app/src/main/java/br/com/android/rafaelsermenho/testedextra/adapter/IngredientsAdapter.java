package br.com.android.rafaelsermenho.testedextra.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.android.rafaelsermenho.testedextra.R;
import br.com.android.rafaelsermenho.testedextra.model.Ingredient;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsHolder> {
    private final Context context;
    private List<Ingredient> ingredientList = new ArrayList<>();
    private final IQuantityChangeListener mListener;

    public IngredientsAdapter(Context context, List<Ingredient> ingredients, IQuantityChangeListener listener) {
        this.context = context;
        ingredientList = ingredients;
        mListener = listener;
    }

    @Override
    public IngredientsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item_row, parent, false);
        return new IngredientsHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(IngredientsHolder ingredientsHolder, int position) {
        ingredientsHolder.txtNomeIngrediente.setText(ingredientList.get(position).getName());
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        ingredientsHolder.txtPrecoIngrediente.setText(format.format(ingredientList.get(position).getPrice()));
        Glide.with(context).load(ingredientList.get(position).getImage()).fitCenter().into(ingredientsHolder.imgFotoIngrediente);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    @Override
    public long getItemId(int position) {
        return ingredientList.get(position).getId();
    }

    public class IngredientsHolder extends RecyclerView.ViewHolder {
        final TextView txtNomeIngrediente;
        final TextView txtPrecoIngrediente;
        final ImageView imgFotoIngrediente;
        final EditText edtQuantidade;
        private final IQuantityChangeListener mListener;

        public IngredientsHolder(View itemView, IQuantityChangeListener listener) {
            super(itemView);

            mListener = listener;

            txtNomeIngrediente = (TextView) itemView.findViewById(R.id.nomeIngrediente);
            txtPrecoIngrediente = (TextView) itemView.findViewById(R.id.precoIngrediente);
            imgFotoIngrediente = (ImageView) itemView.findViewById(R.id.fotoIngrediente);
            edtQuantidade = (EditText) itemView.findViewById(R.id.edtQuantidade);
            edtQuantidade.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (((EditText) v).getText().length() > 0) {
                        mListener.onQuantityChanged(Integer.parseInt(((EditText) v).getText().toString()), getAdapterPosition());
                    }
                }
            });
        }

    }
}