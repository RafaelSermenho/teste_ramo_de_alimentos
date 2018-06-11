package br.com.android.rafaelsermenho.testedextra.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.android.rafaelsermenho.testedextra.R;
import br.com.android.rafaelsermenho.testedextra.model.Sandwich;

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.SandwichHolder> {
    private final Context context;
    private List<Sandwich> list = new ArrayList<>();
    private final IClickListener mListener;

    public SandwichAdapter(Context context, List<Sandwich> sandwichList, IClickListener listener) {
        this.context = context;
        list = sandwichList;
        mListener = listener;
    }

    @Override
    public SandwichHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sandwich_item_row, parent, false);
        return new SandwichHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(SandwichHolder sandwichHolder, int position) {
        sandwichHolder.txtNomeLanche.setText(list.get(position).getName());
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        sandwichHolder.txtPrecoLanche.setText(format.format(list.get(position).getSandwichPrice()));
        sandwichHolder.txtIngredientesLanche.setText(list.get(position).getIngredientDescription());
        Glide.with(context).load(list.get(position).getImage()).fitCenter().into(sandwichHolder.imgFotoLanche);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    public class SandwichHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView txtNomeLanche;
        final TextView txtPrecoLanche;
        final TextView txtIngredientesLanche;
        final ImageView imgFotoLanche;
        private final IClickListener mListener;

        public SandwichHolder(View itemView, IClickListener listener) {
            super(itemView);

            mListener = listener;
            itemView.setOnClickListener(this);

            txtNomeLanche = (TextView) itemView.findViewById(R.id.nomeLanche);
            txtPrecoLanche = (TextView) itemView.findViewById(R.id.precoLanche);
            txtIngredientesLanche = (TextView) itemView.findViewById(R.id.ingredientesLanche);
            imgFotoLanche = (ImageView) itemView.findViewById(R.id.fotoIngrediente);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v, getAdapterPosition());
        }
    }
}