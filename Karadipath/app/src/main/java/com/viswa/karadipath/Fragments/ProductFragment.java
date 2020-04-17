package com.viswa.karadipath.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.viswa.karadipath.Adapter.ProductsAdapter;
import com.viswa.karadipath.BuildConfig;
import com.viswa.karadipath.Common.BaseFragment;
import com.viswa.karadipath.Common.Constants;
import com.viswa.karadipath.Common.GlobalCache;
import com.viswa.karadipath.Model.ProductListResponse;
import com.viswa.karadipath.Network.APIService;
import com.viswa.karadipath.Network.RetrofitClient;
import com.viswa.karadipath.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, RadioGroup.OnCheckedChangeListener {
    View view;
    Activity thisActivity;

    APIService apiService;

    GlobalCache globalCache;

    RadioGroup viewByGroup;
    RadioButton gridRadio;
    RadioButton listRadio;

    RecyclerView productRecycler;
    TextView emptyLayout;

    SwipeRefreshLayout swipeRefreshLayout;


    List<ProductListResponse.SearchList> productList = new ArrayList<>();
    ProductsAdapter productsAdapter;

    int mColumnCount = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.product_fragment, container, false);
        thisActivity = getActivity();

        apiService = RetrofitClient.getClient(BuildConfig.BASE_URL).create(APIService.class);


        globalCache = GlobalCache.getInstance();
        initialize();
        return view;
    }

    private void initialize() {

        viewByGroup = (RadioGroup) view.findViewById(R.id.view_by_group);
        gridRadio = (RadioButton) view.findViewById(R.id.grid_radio);
        listRadio = (RadioButton) view.findViewById(R.id.list_radio);
        productRecycler = (RecyclerView) view.findViewById(R.id.product_recycler);
        emptyLayout = (TextView) view.findViewById(R.id.empty_layout);

        viewByGroup.setOnCheckedChangeListener(this);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


        setLayout();

        makeServiceCallGetProducts();

    }


    private void makeServiceCallGetProducts() {
        showProgressDialog(Constants.LOADING);

        apiService.getProducts(Constants.API_KEY).enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(Call<ProductListResponse> call, Response<ProductListResponse> response) {
                if (response.isSuccessful()) {
                    ProductListResponse productListResponse = response.body();
                    if (productListResponse.getErrcode().equals("200")) {
                        productList = productListResponse.getSearchList();
                        try {
                            swipeRefreshLayout.setRefreshing(false);
                        }catch (Exception e){
                            e.printStackTrace();
                        }


                        if (productList != null && productList.size() > 0) {
                            emptyLayout.setVisibility(View.GONE);
                            productRecycler.setVisibility(View.VISIBLE);
                            swipeRefreshLayout.setVisibility(View.VISIBLE);

                            productsAdapter = new ProductsAdapter(thisActivity, productList);
                            productRecycler.setAdapter(productsAdapter);

                        } else {
                            emptyLayout.setVisibility(View.VISIBLE);
                            productRecycler.setVisibility(View.GONE);
                            swipeRefreshLayout.setVisibility(View.GONE);

                        }
                        dismissProgressDialog();
                    } else {
                        dismissProgressDialog();
                        Toast.makeText(thisActivity, Constants.OOPS_SOMETHING_WENT_WRONG, Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ProductListResponse> call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(thisActivity, Constants.OOPS_SOMETHING_WENT_WRONG, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onRefresh() {
        makeServiceCallGetProducts();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.grid_radio:
                mColumnCount = 2;
                setLayout();
                break;

            case R.id.list_radio:
                mColumnCount = 1;
                setLayout();
                break;
        }
    }

    public boolean isTablet() {
        try {
            // Compute screen size
            DisplayMetrics dm = thisActivity.getResources().getDisplayMetrics();
            float screenWidth  = dm.widthPixels / dm.xdpi;
            float screenHeight = dm.heightPixels / dm.ydpi;
            double size = Math.sqrt(Math.pow(screenWidth, 2) +
                    Math.pow(screenHeight, 2));
            return size >= 7;
        } catch(Throwable t) {
            return false;
        }

    }


    private void setLayout() {

        if(isTablet()){
            if (mColumnCount == 1) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(thisActivity);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                productRecycler.setLayoutManager(layoutManager);
            } else {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(thisActivity, 4);
                gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                productRecycler.setLayoutManager(gridLayoutManager);

            }
        } else {
            if (mColumnCount == 1) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(thisActivity);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                productRecycler.setLayoutManager(layoutManager);
            } else {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(thisActivity, mColumnCount);
                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                productRecycler.setLayoutManager(gridLayoutManager);

            }
        }
    }
}
