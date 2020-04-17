package com.viswa.karadipath.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.viswa.karadipath.Common.Constants;
import com.viswa.karadipath.Model.ProductListResponse;
import com.viswa.karadipath.R;
import com.viswa.karadipath.ViewHolder.ProductsViewHolder;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsViewHolder> {

    Activity thisActivity;
    View itemView = null;
    List<ProductListResponse.SearchList> productList;

    public ProductsAdapter(Activity thisActivity, List<ProductListResponse.SearchList> productList) {
        this.productList = productList;
        this.thisActivity = thisActivity;
    }


    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
        return new ProductsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, final int position) {
        try {

            if (productList.get(position).getProductTitle() != null) {
                holder.productNameTxt.setText(productList.get(position).getProductTitle());
            } else {
                holder.productNameTxt.setText(Constants.EMPTY_STRING);
            }

            if (productList.get(position).getCategoryType() != null) {
                holder.categoryTxt.setText(productList.get(position).getCategoryType());
            } else {
                holder.categoryTxt.setText(Constants.EMPTY_STRING);
            }

            if (productList.get(position).getOriginalPrice() != null) {
                String originalPrice = String.format(thisActivity.getResources().getString(R.string.offer_price), productList.get(position).getOriginalPrice());
                holder.originalPriceTxt.setText(originalPrice);
            } else {
                holder.originalPriceTxt.setText(Constants.EMPTY_STRING);
            }

            if (productList.get(position).getFinalPrice() != null) {
                holder.offerPriceTxt.setText(productList.get(position).getFinalPrice());
            } else {
                holder.offerPriceTxt.setText(Constants.EMPTY_STRING);
            }

            if (productList.get(position).getStockStatus().equals(Constants.OUTOFSTOCK)) {
                holder.outOfStockTxt.setVisibility(View.VISIBLE);
            } else {
                holder.outOfStockTxt.setVisibility(View.GONE);
            }


            String imageUrl = productList.get(position).getThumbImg();
            Picasso.get().load(imageUrl).placeholder(R.drawable.images).into(holder.productImage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


}