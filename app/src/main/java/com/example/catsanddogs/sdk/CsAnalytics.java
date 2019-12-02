package com.example.catsanddogs.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catsanddogs.BuildConfig;
import com.example.catsanddogs.R;

import java.util.ArrayList;
import java.util.List;

public class CsAnalytics {

    private Context context;
    private DebounceClickHandler debounceClickHandler;
    private List<CharSequence> pets;
    private final String logFilePath = "petLog.txt";
    private PetFileObserver observer;
    private boolean isFileChecked = false;
    private boolean logEnabled;


    private EventListener debouncedEventListener = new EventListener() {
        @Override
        public void onEventHandledAfterDelay(Object... args) {

            RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) args[0];
            int position = (int) args[1];

            Context context = holder.itemView.getContext();
            if (context == null) return;

            final CharSequence pet = getContent(holder);
            final int countOfPets = getCountInRange(pet, holder.getAdapterPosition());
            final String message = context.getString(R.string.position_clicked, (holder.getAdapterPosition() + 1), countOfPets, pet);

            if (logEnabled) FileUtil.writeToFile(context, logFilePath, message);
        }
    };


    public CsAnalytics(@NonNull Context context) {
        this.context = context;
        final long TWO_SECONDS = 2000;
        debounceClickHandler = new DebounceClickHandler(debouncedEventListener, TWO_SECONDS);
        pets = new ArrayList<>();
        setLogFile();
        logEnabled = BuildConfig.FLAVOR == "dev";
    }




    public void trigger(@NonNull RecyclerView.ViewHolder holder, int position) {

        Context context = holder.itemView.getContext();
        if (context == null) return;

        final CharSequence pet = getContent(holder);
        final int countOfPets = getCountInRange(pet, holder.getAdapterPosition());
        final String message = context.getString(R.string.position_clicked, (holder.getAdapterPosition() + 1), countOfPets, pet);
        Toast.makeText(
                context,
                message,
                Toast.LENGTH_LONG
        ).show();
        debounceClickHandler.debounceTrigger(holder, position);

    }

    public void track(@NonNull RecyclerView.ViewHolder holder, int position) {
        pets.add(getContent(holder));
    }

    private CharSequence getContent(RecyclerView.ViewHolder holder) {
        View view = holder.itemView.findViewById(R.id.pet);
        if (view != null) {
            return view.getContentDescription();
        } else {
            throw new RuntimeException("No data to determine kind of pet");
        }
    }

    public void clear() {
        if (!isFileChecked) {
            //clear
        }

    }

    private int getCountInRange(CharSequence pet, int positionClicked) {

        if (pets.size() <= positionClicked) {
            throw new IllegalArgumentException("adapter position is out of pets count");
        }
        List<CharSequence> subList = pets.subList(0, ++positionClicked);
        int petCount = 0;
        for (CharSequence p : subList) {
            petCount = petCount + (p == pet ? 1 : 0);
        }
        return petCount;
    }

    private void setLogFile() {

        observer = new PetFileObserver(context.getFileStreamPath(logFilePath).getPath());

        observer.setListener(new FileAccessListener() {
            @Override
            public void modified(final String path) {
                final String dataFromFile = FileUtil.readFromFile(context, logFilePath);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {

                        Log.i("FindFile",  "data - " + dataFromFile);
                        Toast.makeText(
                                context,
                                dataFromFile,
                                Toast.LENGTH_LONG
                        ).show();
                    }
                });
            }
        });
        observer.startWatching();
    }

}
