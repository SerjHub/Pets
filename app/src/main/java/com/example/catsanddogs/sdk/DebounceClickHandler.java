package com.example.catsanddogs.sdk;

import android.os.Handler;

import com.example.catsanddogs.sdk.interfaces.EventListener;

class DebounceClickHandler {
    private EventListener eventListener;

    private Object[] args;
    private boolean isEventDebounce = false;

    DebounceClickHandler(EventListener listener) {
        eventListener = listener;
    }

    void debounceTrigger(final Object... args) {
        this.args = args;

        if (isEventDebounce) return;
        isEventDebounce = true;

        long TWO_SECONDS = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isEventDebounce = false;
                eventListener.onEventHandledAfterDelay(DebounceClickHandler.this.args);
            }
        }, TWO_SECONDS);
    }
}


