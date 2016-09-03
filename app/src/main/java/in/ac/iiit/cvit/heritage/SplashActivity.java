package in.ac.iiit.cvit.heritage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {

    private LaunchPreferenceManager launchPreferenceManager;

    private static final int SLEEP = 5;
    private static final String LOGTAG = "Heritage";

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 2;
    private static final int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 3;
    private static final int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //checkPermissions();
        launchPreferenceManager = new LaunchPreferenceManager(SplashActivity.this);

        Thread background = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(SLEEP*1000);

                    if (!launchPreferenceManager.isFirstTimeLaunch()) {
                        launchPreferenceManager.setFirstTimeLaunch(false);

                        Intent intent_packages_list = new Intent(SplashActivity.this, PackagesListActivity.class);
                        intent_packages_list.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent_packages_list);
                        finish();
                    } else {
                        Intent intent_splash_intro = new Intent(SplashActivity.this, SplashIntroActivity.class);
                        intent_splash_intro.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent_splash_intro);
                        finish();
                    }
                } catch (Exception e) {
                    Log.e(LOGTAG, e.toString());
                }
            }
        };
        background.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent_splash = new Intent(Intent.ACTION_MAIN);
        intent_splash.addCategory(Intent.CATEGORY_HOME);
        intent_splash.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent_splash);

        finish();
        System.exit(0);
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }
    }
}
