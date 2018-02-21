package com.tradesy.android.tradesytest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.ProductType;

/**
 * Created by manolofernandez on 10/13/17.
 */

public class PartOneAdapter extends RecyclerView.Adapter<PartOneAdapter.ProductTypeViewHolder> {

    private List<ProductType> productTypeList;

    public PartOneAdapter(List<ProductType> productTypeList){
        this.productTypeList = productTypeList;
    }

    @Override
    public ProductTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_type, parent, false);
        return new ProductTypeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductTypeViewHolder holder, int position) {
        final ProductType productType = productTypeList.get(position);

        holder.idTextView.setText(productType.getId());
        holder.labelTextView.setText(productType.getLabel());
        holder.nameTextView.setText(productType.getName());
    }

    @Override
    public int getItemCount() {
        return productTypeList.size();
    }

    public static class ProductTypeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_type_id_text)
        TextView idTextView;
        @BindView(R.id.product_type_label_text)
        TextView labelTextView;
        @BindView(R.id.product_type_name_text)
        TextView nameTextView;

        public ProductTypeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
