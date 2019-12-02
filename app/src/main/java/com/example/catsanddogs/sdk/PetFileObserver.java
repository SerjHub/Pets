package com.example.catsanddogs.sdk;

import android.os.FileObserver;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;

public class PetFileObserver extends FileObserver {

    private FileAccessListener listener;

    public PetFileObserver(@NonNull String file) {
        super(file);
    }


    public void setListener(FileAccessListener listener) {
        this.listener = listener;
    }

    @Override
    public void onEvent(int event, @Nullable String path) {
        Log.i("LogPets observer", "event come ");
        switch (event) {
            case PetFileObserver.MODIFY:
                listener.modified(path);
                break;
        }
    }
}

interface FileAccessListener {
    void modified(String path);
}
