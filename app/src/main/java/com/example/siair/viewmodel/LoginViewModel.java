package com.example.siair.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.siair.model.LoginRequestBody;
import com.example.siair.model.LoginResponse;
import com.example.siair.model.User;
import com.example.siair.network.SiairServiceNetwork;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<User> user = new MutableLiveData<User>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
    private SiairServiceNetwork siairServiceNetwork = new SiairServiceNetwork();

    public void setUser (LoginRequestBody body) {
        isLoading.postValue(true);
        siairServiceNetwork.getService().login(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerLoginResponse());
    }

    private Observer<LoginResponse> observerLoginResponse () {
        Observer<LoginResponse> observer = new Observer<LoginResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginResponse loginResponse) {
                if (loginResponse.getStatus()) {
                    user.postValue(loginResponse.getData());
                }
            }

            @Override
            public void onError(Throwable e) {
                isLoading.postValue(false);
                user.postValue(null);
            }

            @Override
            public void onComplete() {
                isLoading.postValue(false);
            }
        };

        return observer;
    }

    public MutableLiveData<User> getUser () {
        return user;
    }

    public MutableLiveData<Boolean> getIsLoading () {
        return isLoading;
    }

}
