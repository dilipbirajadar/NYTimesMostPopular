package com.nytimesmostpopular.network;


import com.nytimesmostpopular.model.mostpopular.NycMostPopularRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Dilip Birajadar on 2019-12-14.
 */
public interface ApiInterface {
    @GET("/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=FRsxD9IBS7oN3fNA6ICId2HWJgeU2zAY")
    Call<String> mostPopular();

}
