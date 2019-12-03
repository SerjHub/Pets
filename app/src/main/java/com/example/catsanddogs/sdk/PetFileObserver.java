package com.example.catsanddogs.sdk;

import android.os.FileObserver;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.catsanddogs.sdk.interfaces.FileAccessListener;

public class PetFileObserver extends FileObserver {

    private FileAccessListener listener;

    public PetFileObserver(@NonNull String file) {
        super(file);  //crash if constructor with File
    }


    public void setListener(FileAccessListener listener) {
        this.listener = listener;
    }

    @Override
    public void onEvent(int event, @Nullable String path) {
        switch (event) {
            case PetFileObserver.MODIFY:
                listener.modified(path);
                break;
        }
    }
}
