package com.threekilogram.wuxio.animatedrawable;

import android.app.Application;

/**
 * @author wuxio 2018-05-21:11:39
 */
public class App extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        // Normal app init code...
    }

}
