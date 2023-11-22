package app.happyhumor.online;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.FirebaseApp;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class UniversalConf extends Application {
    public  static String appURL;

    @Override
    public void onCreate() {
        super.onCreate();


    }

    public static void setupRemoteConfig(Context context, Activity activity, Boolean hasFirebase) {
        if (Boolean.TRUE.equals(hasFirebase)) {
            FirebaseApp.initializeApp(context);
            FirebaseRemoteConfig remoteCFG = FirebaseRemoteConfig.getInstance();
            FirebaseRemoteConfigSettings settingsCFG = new FirebaseRemoteConfigSettings.Builder()
                    .setMinimumFetchIntervalInSeconds(3600)
                    .build();

            remoteCFG.setConfigSettingsAsync(settingsCFG);

            remoteCFG.fetchAndActivate().addOnCompleteListener(activity, task -> {
                if (task.isSuccessful()) {
                    Log.d("FirebaseCFG:", "Loading Successful");
                    appURL = remoteCFG.getString("appURL");

                    String endPoint = appURL;
                    Log.d("WZ", appURL);
                    RequestQueue requestQueue = Volley.newRequestQueue(context);

                    // Now you can use the 'gameURL' fetched from remote config to make an HTTP request.

                } else {
                    Log.e("FirebaseCFG:", "Loading not Successful", task.getException());
                }
            });
        }
    }
}