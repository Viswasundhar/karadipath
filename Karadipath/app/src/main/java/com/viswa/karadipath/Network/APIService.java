package com.viswa.karadipath.Network;

import com.viswa.karadipath.Model.ProductListResponse;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by VichU on 17-04-2020.
 */

public interface APIService {

    @POST("api/search/results/")
    Call<ProductListResponse> getProducts(@Header("X-API-KEY") String apiKey);


}

