package com.example.siair.network;

import com.example.siair.model.LaporanAllResponse;
import com.example.siair.model.LoginRequestBody;
import com.example.siair.model.LoginResponse;
import com.example.siair.model.TagihanAllResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SiairService {

    @POST("login")
    Observable<LoginResponse> login(@Body LoginRequestBody body);

    @GET("tagihan")
    Observable<TagihanAllResponse> tagihanAll();

    @GET("laporan")
    Observable<LaporanAllResponse> laporanAll();
}
