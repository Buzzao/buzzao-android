package com.igormatos.buzzao;

import android.app.Application;

import com.parse.Parse;

/**
 * @author Igor Matos
 */
public class Buzzao extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);

        // Iniciar Parse
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));

    }

}
