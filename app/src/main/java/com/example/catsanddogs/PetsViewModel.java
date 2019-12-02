package com.example.catsanddogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PetsViewModel extends ViewModel {
    private final MutableLiveData<String[]> pets = new MutableLiveData<>();
    public final LiveData<String[]> petsLiveData = pets;



    public void viewPrepared() {

    }
}
