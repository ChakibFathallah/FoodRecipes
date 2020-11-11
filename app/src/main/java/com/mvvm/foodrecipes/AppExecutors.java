package com.mvvm.foodrecipes;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {

    private static AppExecutors instance;


    public static AppExecutors getInstance(){
        if (instance == null){
            instance = new AppExecutors();
        }
        return instance;
    }

    // private Executor mBackgroundExecutor = Executors.newSingleThreadExecutor();
    // private Executor mBackgroundExecutor = Executors.newFixedThreadPool(3);
    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService networkIO() {
        return mNetworkIO;
    }

    public AppExecutors() {
        //execute thread
    /*    mBackgroundExecutor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });*/

    }
}
