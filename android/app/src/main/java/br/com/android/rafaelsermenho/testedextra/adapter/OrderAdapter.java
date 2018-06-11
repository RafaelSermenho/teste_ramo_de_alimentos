package br.com.android.rafaelsermenho.testedextra.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.android.rafaelsermenho.testedextra.R;
import br.com.android.rafaelsermenho.testedextra.model.Order;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {
    private List<Order> orderList = new ArrayList<>();
    private Context context;

    public OrderAdapter(Context context, List<Order> orders) {
        this.context = context;
        orderList = orders;
    }

    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item_row, parent, false);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderHolder orderHolder, int position) {
        orderHolder.txtNomeLanche.setText(orderList.get(position).getSandwich().getName());
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        orderHolder.txtPrecoLanche.setText(format.format(orderList.get(position).getSandwich().getSandwichPrice()));
        orderHolder.txtIngredientesLanche.setText(orderList.get(position).getSandwich().getIngredientDescription());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    @Override
    public long getItemId(int position) {
        return orderList.get(position).getId();
    }

    public class OrderHolder extends RecyclerView.ViewHolder {
        final TextView txtNomeLanche;
        final TextView txtPrecoLanche;
        final TextView txtIngredientesLanche;

        public OrderHolder(View itemView) {
            super(itemView);

            txtNomeLanche = (TextView) itemView.findViewById(R.id.nomeLanche);
            txtPrecoLanche = (TextView) itemView.findViewById(R.id.precoLanche);
            txtIngredientesLanche = (TextView) itemView.findViewById(R.id.ingredientesLanche);
        }

    }
}