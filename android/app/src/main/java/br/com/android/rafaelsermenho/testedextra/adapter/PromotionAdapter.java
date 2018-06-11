package br.com.android.rafaelsermenho.testedextra.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.android.rafaelsermenho.testedextra.R;
import br.com.android.rafaelsermenho.testedextra.model.Promotion;

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.PromotionHolder> {
    private Context context;
    private List<Promotion> promotionList = new ArrayList<>();

    public PromotionAdapter(Context context, List<Promotion> promotions) {
        this.context = context;
        this.promotionList = promotions;
    }

    @Override
    public PromotionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.promotion_item_row, parent, false);
        return new PromotionHolder(view);
    }

    @Override
    public void onBindViewHolder(PromotionHolder promotionHolder, int position) {
        promotionHolder.txtNomePromocao.setText(promotionList.get(position).getName());
        promotionHolder.txtDescricaoPromocao.setText(promotionList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return promotionList.size();
    }

    @Override
    public long getItemId(int position) {
        return promotionList.get(position).getId();
    }

    public class PromotionHolder extends RecyclerView.ViewHolder {
        final TextView txtNomePromocao;
        final TextView txtDescricaoPromocao;

        public PromotionHolder(View itemView) {
            super(itemView);

            txtNomePromocao = (TextView) itemView.findViewById(R.id.nomePromocao);
            txtDescricaoPromocao = (TextView) itemView.findViewById(R.id.descricaoPromocao);
        }

    }
}