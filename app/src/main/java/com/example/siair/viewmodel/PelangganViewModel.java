package com.example.siair.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.siair.model.Pelanggan;
import com.example.siair.model.PelangganResponse;
import com.example.siair.model.Tagihan;
import com.example.siair.model.TagihanAllResponse;
import com.example.siair.model.TagihanPelangganResponse;
import com.example.siair.network.SiairServiceNetwork;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PelangganViewModel extends ViewModel {

    private MutableLiveData<Pelanggan> pelanggan = new MutableLiveData<Pelanggan>();
    private MutableLiveData<Integer> idPelanggan = new MutableLiveData<Integer>();
    private MutableLiveData<Tagihan> tagihan = new MutableLiveData<Tagihan>();
    private MutableLiveData<ArrayList<Tagihan>> laporan = new MutableLiveData<ArrayList<Tagihan>>();
    private MutableLiveData<Boolean> isTagihanLoading = new MutableLiveData<Boolean>();
    private MutableLiveData<Boolean> isLaporanLoading = new MutableLiveData<Boolean>();
    private SiairServiceNetwork siairServiceNetwork = new SiairServiceNetwork();

    public PelangganViewModel() {
        idPelanggan.postValue(null);
    }

    public void setIdPelanggan (String rekening) {
        siairServiceNetwork.getService().pelangganByRekening(rekening)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerPelangganResponse());
    }

    public void setTagihanPelanggan (String id) {
        isTagihanLoading.postValue(true);
        siairServiceNetwork.getService().tagihanPelanggan(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerTagihanPelangganResponse());
    }

    public void setLaporanPelanggan (String id) {
        isLaporanLoading.postValue(true);
        siairServiceNetwork.getService().laporanPelanggan(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerLaporanPelangganResponse());
    }

    private Observer<PelangganResponse> observerPelangganResponse () {
        Observer<PelangganResponse> observer = new Observer<PelangganResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PelangganResponse pelangganResponse) {
                idPelanggan.postValue(pelangganResponse.getData().getId());
                pelanggan.postValue(pelangganResponse.getData());
            }

            @Override
            public void onError(Throwable e) {
                idPelanggan.postValue(null);
            }

            @Override
            public void onComplete() {

            }
        };

        return observer;
    }

    private Observer<TagihanPelangganResponse> observerTagihanPelangganResponse () {
        Observer<TagihanPelangganResponse> observer = new Observer<TagihanPelangganResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TagihanPelangganResponse tagihanPelangganResponse) {
                tagihan.postValue(tagihanPelangganResponse.getData());
                isTagihanLoading.postValue(false);
            }

            @Override
            public void onError(Throwable e) {
                tagihan.postValue(null);
                isTagihanLoading.postValue(false);
            }

            @Override
            public void onComplete() {

            }
        };

        return observer;
    }

    private Observer<TagihanAllResponse> observerLaporanPelangganResponse () {
        Observer<TagihanAllResponse> observer = new Observer<TagihanAllResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TagihanAllResponse laporanPelangganResponse) {
                ArrayList<Tagihan> laporanArrayList = new ArrayList<Tagihan>();
                laporanArrayList.addAll(laporanPelangganResponse.getData());
                laporan.postValue(laporanArrayList);
                isLaporanLoading.postValue(false);
            }

            @Override
            public void onError(Throwable e) {
                laporan.postValue(null);
                isLaporanLoading.postValue(false);
            }

            @Override
            public void onComplete() {

            }
        };

        return observer;
    }

    public MutableLiveData<Integer> getIdPelanggan() {
        return idPelanggan;
    }

    public MutableLiveData<Tagihan> getTagihane() {
        return tagihan;
    }

    public MutableLiveData<ArrayList<Tagihan>> getLaporan() {
        return laporan;
    }

    public MutableLiveData<Boolean> getIsLaporanLoading() {
        return isLaporanLoading;
    }

    public MutableLiveData<Boolean> getIsTagihanLoading () {
        return isTagihanLoading;
    }

    public int getIdPelangganValue () {
        return idPelanggan.getValue();
    }

}
