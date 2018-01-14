package com.company.expandablerecyclerview.network;


import com.company.expandablerecyclerview.entity.MainResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Andrew on 14.01.2018.
 */

public interface ItemsApi {


    @GET("/list")
    Observable<MainResponse> getMainResponse();
}
