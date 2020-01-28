package com.example.siair.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.siair.model.Laporan;
import com.example.siair.model.LaporanAllResponse;
import com.example.siair.network.SiairServiceNetwork;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LaporanViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Laporan>> laporanAll = new MutableLiveData<ArrayList<Laporan>>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
    private SiairServiceNetwork siairServiceNetwork = new SiairServiceNetwork();

    public void setLaporanAll() {
        isLoading.postValue(true);
        siairServiceNetwork.getService().laporanAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerLaporanAll());
    }

    private Observer<LaporanAllResponse> observerLaporanAll () {
        Observer<LaporanAllResponse> observer = new Observer<LaporanAllResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LaporanAllResponse laporanAllResponse) {
                if (laporanAllResponse.getStatus()) {
                    ArrayList<Laporan> laporanArrayList = new ArrayList<Laporan>();
                    laporanArrayList.addAll(laporanAllResponse.getData());
                    laporanAll.postValue(laporanArrayList);
                }
            }

            @Override
            public void onError(Throwable e) {
                isLoading.postValue(false);
                laporanAll.postValue(null);
            }

            @Override
            public void onComplete() {
                isLoading.postValue(false);
            }
        };

        return observer;
    }

    public MutableLiveData<ArrayList<Laporan>> getLaporanAll () {
        return laporanAll;
    }

    public MutableLiveData<Boolean> getIsLoading () {
        return isLoading;
    }
}
