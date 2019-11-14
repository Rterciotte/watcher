package com.example.tcc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class TestService extends Service
{
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        // START_STICKY serve para executar o serviço até que o usuário decida pará-lo, é reiniciado automaticamente sempre que termina
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}