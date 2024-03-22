package com.muratcangzm.blacktooth.di;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {


    @Singleton
    @Provides
    public Context provideContext(@ApplicationContext Context context) {
        return context;
    }

    @Singleton
    @Provides
    public Activity provideActivity(Activity activity){
        return activity;
    }

    @Singleton
    @Provides
    public BluetoothAdapter provideBluetoothAdapter(){
        return BluetoothAdapter.getDefaultAdapter();
    }


}
