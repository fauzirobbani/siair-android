package com.example.siair.network;

import com.example.siair.model.LaporanAllResponse;
import com.example.siair.model.LoginRequestBody;
import com.example.siair.model.LoginResponse;
import com.example.siair.model.PelangganAllResponse;
import com.example.siair.model.PelangganByIdResponse;
import com.example.siair.model.PelangganResponse;
import com.example.siair.model.TagihanAllResponse;
import com.example.siair.model.TagihanPelangganResponse;
import com.example.siair.model.TagihanRequestBody;
import com.example.siair.model.TambahTagihanResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SiairService {

    @POST("login")
    Observable<LoginResponse> login(@Body LoginRequestBody body);

    @POST("create_tagihan")
    Observable<TambahTagihanResponse> storeTagihan(@Body TagihanRequestBody body);

    @GET("tagihan")
    Observable<TagihanAllResponse> tagihanAll();

    @GET("laporan")
    Observable<LaporanAllResponse> laporanAll();

    @GET("pelanggan_all")
    Observable<PelangganAllResponse> pelangganAll();

    @GET("pelanggan/{pelanggan_id}")
    Observable<PelangganByIdResponse> pelanggan(@Path("pelanggan_id") String id);

    @GET("pelanggan/rekening/{rekening}")
    Observable<PelangganResponse> pelangganByRekening(@Path("rekening") String rekening);

    @GET("tagihan/{pelanggan_id}")
    Observable<TagihanPelangganResponse> tagihanPelanggan(@Path("pelanggan_id") String id);

    @GET("laporan/{pelanggan_id}")
    Observable<TagihanAllResponse> laporanPelanggan(@Path("pelanggan_id") String id);
}
