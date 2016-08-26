package in.ac.iiit.cvit.heritage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {

    private static int SLEEP = 3;
    private static final String LOGTAG = "Heritage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread background = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(SLEEP*1000);

                    Intent intent_packages_list = new Intent(SplashActivity.this, PackagesListActivity.class);
                    intent_packages_list.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent_packages_list);

                    finish();
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
}
