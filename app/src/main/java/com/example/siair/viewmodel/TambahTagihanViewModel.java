package com.example.siair.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.siair.model.Pelanggan;
import com.example.siair.model.PelangganAllResponse;
import com.example.siair.network.SiairServiceNetwork;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TambahTagihanViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Pelanggan>> pelangganAll = new MutableLiveData<ArrayList<Pelanggan>>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
    private SiairServiceNetwork siairServiceNetwork = new SiairServiceNetwork();

    public void setPelangganAll() {
        isLoading.postValue(true);
        siairServiceNetwork.getService().pelangganAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerPelangganAll());
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

    public MutableLiveData<ArrayList<Pelanggan>> getLaporanAll () {
        return pelangganAll;
    }

    public MutableLiveData<Boolean> getIsLoading () {
        return isLoading;
    }

}
