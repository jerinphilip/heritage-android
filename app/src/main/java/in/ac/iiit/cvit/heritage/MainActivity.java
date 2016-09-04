package in.ac.iiit.cvit.heritage;

import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SessionManager sessionManager;

    public static ArrayList<InterestPoint> interestPoints;

    private static final String LOGTAG = "Heritage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager();
        String packageName = sessionManager.getStringSessionPreferences(MainActivity.this, "package_name", "");
        interestPoints = LoadPackage(packageName);

        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.home));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.nearby));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.places));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

           }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent_settings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent_settings);
                break;
            case R.id.action_about_us:
                Intent intent_about_us = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent_about_us);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public ArrayList<InterestPoint> LoadPackage(String packageName){
        PackageReader reader;
        packageName = packageName.toLowerCase();
        reader = new PackageReader(packageName);
        ArrayList<InterestPoint> interestPoints = reader.getContents();
        return interestPoints;
    }
}
