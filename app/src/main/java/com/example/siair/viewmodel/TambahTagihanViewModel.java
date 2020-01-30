package com.example.siair.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.siair.model.Pelanggan;
import com.example.siair.model.PelangganAllResponse;
import com.example.siair.model.PelangganByIdResponse;
import com.example.siair.model.TagihanRequestBody;
import com.example.siair.model.TambahTagihanResponse;
import com.example.siair.network.SiairServiceNetwork;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TambahTagihanViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Pelanggan>> pelangganAll = new MutableLiveData<ArrayList<Pelanggan>>();
    private MutableLiveData<Pelanggan> pelanggan = new MutableLiveData<Pelanggan>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
    private MutableLiveData<Boolean> isSending = new MutableLiveData<Boolean>();
    private MutableLiveData<Boolean> isSuccessSending = new MutableLiveData<Boolean>();
    private SiairServiceNetwork siairServiceNetwork = new SiairServiceNetwork();

    public void setPelangganAll () {
        isLoading.postValue(true);
        siairServiceNetwork.getService().pelangganAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerPelangganAll());
    }

    public void setPelanggan (String id) {
        siairServiceNetwork.getService().pelanggan(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerPelangganById());
    }

    public void storeTagihan (TagihanRequestBody body) {
        isSending.postValue(true);
        siairServiceNetwork.getService().storeTagihan(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerTambahTagihan());
    }

    private Observer<PelangganAllResponse> observerPelangganAll () {
        Observer<PelangganAllResponse> observer = new Observer<PelangganAllResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PelangganAllResponse pelangganAllResponse) {
                if (pelangganAllResponse.getStatus()) {
                    ArrayList<Pelanggan> laporanArrayList = new ArrayList<Pelanggan>();
                    laporanArrayList.addAll(pelangganAllResponse.getData());
                    pelangganAll.postValue(laporanArrayList);
                }
            }

            @Override
            public void onError(Throwable e) {
                isLoading.postValue(false);
                pelangganAll.postValue(null);
            }

            @Override
            public void onComplete() {
                isLoading.postValue(false);
            }
        };

        return observer;
    }

    private Observer<PelangganByIdResponse> observerPelangganById () {
        Observer<PelangganByIdResponse> observer = new Observer<PelangganByIdResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                pelanggan.postValue(null);
            }

            @Override
            public void onNext(PelangganByIdResponse pelangganByIdResponse) {
                if (pelangganByIdResponse.getStatus()) {
                    pelanggan.postValue(pelangganByIdResponse.getData().getPelanggan());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        return observer;
    }

    private Observer<TambahTagihanResponse> observerTambahTagihan () {
        Observer<TambahTagihanResponse> observer = new Observer<TambahTagihanResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TambahTagihanResponse tambahTagihanResponse) {
                if (tambahTagihanResponse.getStatus()) {
                    isSuccessSending.postValue(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                isSending.postValue(false);
                isSuccessSending.postValue(false);
            }

            @Override
            public void onComplete() {
                isSending.postValue(false);
            }
        };

        return observer;
    }

    public MutableLiveData<ArrayList<Pelanggan>> getPelangganAll () {
        return pelangganAll;
    }

    public MutableLiveData<Pelanggan> getPelanggan () {
        return pelanggan;
    }

    public MutableLiveData<Boolean> getIsLoading () {
        return isLoading;
    }

    public MutableLiveData<Boolean> getIsSending () {
        return isSending;
    }

    public MutableLiveData<Boolean> getIsSuccessSending() {
        return isSuccessSending;
    }

    public Pelanggan getPelangganValue () {
        return pelanggan.getValue();
    }

}
