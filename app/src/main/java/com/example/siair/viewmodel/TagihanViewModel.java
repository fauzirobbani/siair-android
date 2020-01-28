package com.example.siair.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.siair.model.Tagihan;
import com.example.siair.model.TagihanAllResponse;
import com.example.siair.network.SiairServiceNetwork;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TagihanViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Tagihan>> tagihanAll = new MutableLiveData<ArrayList<Tagihan>>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
    private SiairServiceNetwork siairServiceNetwork = new SiairServiceNetwork();

    public void setTagihanAll() {
        isLoading.postValue(true);
        siairServiceNetwork.getService().tagihanAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerTagihanAll());
    }

    private Observer<TagihanAllResponse> observerTagihanAll () {
        Observer<TagihanAllResponse> observer = new Observer<TagihanAllResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TagihanAllResponse tagihanAllResponse) {
                if (tagihanAllResponse.getStatus()) {
                    ArrayList<Tagihan> tagihanArrayList = new ArrayList<Tagihan>();
                    tagihanArrayList.addAll(tagihanAllResponse.getData());
                    tagihanAll.postValue(tagihanArrayList);
                }
            }

            @Override
            public void onError(Throwable e) {
                isLoading.postValue(false);
                tagihanAll.postValue(null);
            }

            @Override
            public void onComplete() {
                isLoading.postValue(false);
            }
        };

        return observer;
    }

    public MutableLiveData<ArrayList<Tagihan>> getTagihanAll () {
        return tagihanAll;
    }

    public MutableLiveData<Boolean> getIsLoading () {
        return isLoading;
    }

}
