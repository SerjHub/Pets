package com.example.catsanddogs.sdk;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class DebounceClickHandler {
    private EventListener eventListener;

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    DebounceClickHandler(EventListener listener, long delayTime) {
        delay = delayTime;
        eventListener = listener;
    }

    private long delay;
    private long timeStamp;

    void debounceTrigger(final Object... args) {

      //  scheduler.

        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                eventListener.onEventHandledAfterDelay(args);
            }
        }, 2, TimeUnit.SECONDS);
    }

    private Thread launchTimer(final Runnable r) {
        return new Thread(new Runnable() {
            @Override
            public void run() {


            }
        });
    }

}

interface EventListener {
    void onEventHandledAfterDelay(Object... args);
}
