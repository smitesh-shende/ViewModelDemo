package com.gl.viewmodeldemo.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import com.gl.viewmodeldemo.network.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

/**
 * Live data model.
 */
public class UsersViewModel extends ViewModel {
    /**
     * Mutable live data object.
     */
    private MutableLiveData<List<User>> mUsersLiveData = new MutableLiveData<>();
    /**
     * Provides users from URL.
     * @return list of users
     */
    public LiveData<List<User>> getUsers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<List<User>> call = api.getHeroes();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mUsersLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("UsersViewModel", "onFailure: " + t.getMessage());
            }
        });
        return mUsersLiveData;
    }
}
