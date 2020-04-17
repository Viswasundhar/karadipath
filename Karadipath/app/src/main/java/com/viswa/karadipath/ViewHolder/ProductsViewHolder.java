package com.viswa.karadipath.ViewHolder;

import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.viswa.karadipath.R;

public class ProductsViewHolder extends RecyclerView.ViewHolder{

    public AppCompatTextView productNameTxt;
    public AppCompatTextView categoryTxt;
    public AppCompatTextView offerPriceTxt;
    public AppCompatTextView originalPriceTxt;
    public AppCompatTextView ageGroupTxt;
    public AppCompatTextView outOfStockTxt;
    public AppCompatImageView productImage;

    public ProductsViewHolder(View itemView) {
        super(itemView);

        productNameTxt = (AppCompatTextView) itemView.findViewById(R.id.product_name_txt);
        categoryTxt = (AppCompatTextView) itemView.findViewById(R.id.category_txt);
        offerPriceTxt = (AppCompatTextView) itemView.findViewById(R.id.offer_price_txt);
        originalPriceTxt = (AppCompatTextView) itemView.findViewById(R.id.original_price_txt);
        ageGroupTxt = (AppCompatTextView) itemView.findViewById(R.id.age_group_txt);
        outOfStockTxt = (AppCompatTextView) itemView.findViewById(R.id.out_of_stock_txt);

        productImage = (AppCompatImageView) itemView.findViewById(R.id.product_image);


    }
}

