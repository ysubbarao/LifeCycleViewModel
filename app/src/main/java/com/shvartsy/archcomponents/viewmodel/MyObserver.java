package com.shvartsy.archcomponents.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;



/**
 * Demonstrates usage of basic LifecycleObserver.
 */
public class MyObserver implements LifecycleObserver {
    private static final String LOG_TAG = MyObserver.class.getSimpleName();



    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.d(LOG_TAG, "LifecycleObserver.Event.ON_CREATE.");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Log.d(LOG_TAG, "LifecycleObserver.Event.ON_START.");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.d(LOG_TAG, "LifecycleObserver.Event.ON_PAUSE.");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.d(LOG_TAG, "LifecycleObserver.Event.ON_RESUME.");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.d(LOG_TAG, "LifecycleObserver.Event.ON_STOP.");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestoy() {
        Log.d(LOG_TAG, "LifecycleObserver.Event.ON_DESTROY");
    }


}
