package in.ac.iiit.cvit.heritage;

import android.*;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class PackagesListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button button_download_packages;
    private ListView listview_package_list;
    private SessionManager sessionManager;

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 2;
    private static final int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 3;
    private static final int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_lists);

        for (int i=1; i<=4; i++) {
            checkPermissions(i);
        }

        sessionManager = new SessionManager();

        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitle(R.string.my_heritage_sites);
        setSupportActionBar(toolbar);

        button_download_packages = (Button) findViewById(R.id.button_download_packages);
        button_download_packages.setText(R.string.download);
        button_download_packages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_package_downloader = new Intent(PackagesListActivity.this, PackagesDownloaderActivity.class);
                startActivity(intent_package_downloader);
            }
        });

        //temporary hard coding
        String[] packages = {"Golconda", "Hampi"};
        listview_package_list = (ListView) findViewById(R.id.listview_package_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(PackagesListActivity.this, android.R.layout.simple_list_item_1, packages);
        listview_package_list.setAdapter(adapter);
        listview_package_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view;
                String packageName = (String) textView.getText();
                sessionManager.setSessionPreferences(PackagesListActivity.this, "package_name", packageName);

                Intent intent_main_activity = new Intent(PackagesListActivity.this, MainActivity.class);
                intent_main_activity.putExtra("package", packageName);
                startActivity(intent_main_activity);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:
            case PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(PackagesListActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                        openApplicationPermissions();
                    } else {
                        openApplicationPermissions();
                    }
                }
            }
            case PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
            case PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(PackagesListActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        openApplicationPermissions();
                    } else {
                        openApplicationPermissions();
                    }
                }
            }
        }
    }

    private void checkPermissions(int permissionCode) {

        switch (permissionCode) {
            case 1:
            case 2: {
                if (ContextCompat.checkSelfPermission(PackagesListActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(PackagesListActivity.this);
                            builder.setMessage("The application needs Location Permissions for navigating through the heritage site")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            openApplicationPermissions();
                                        }
                                    });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                    ActivityCompat.requestPermissions(PackagesListActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                }
            }
            case 3:
            case 4: {
                if (ContextCompat.checkSelfPermission(PackagesListActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(PackagesListActivity.this);
                            builder.setMessage("The application needs Location Permissions for navigating through the heritage site")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            openApplicationPermissions();
                                        }
                                    });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                    ActivityCompat.requestPermissions(PackagesListActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
            }
        }
    }

    private void openApplicationPermissions() {
        final Intent intent_permissions = new Intent();
        intent_permissions.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent_permissions.addCategory(Intent.CATEGORY_DEFAULT);
        intent_permissions.setData(Uri.parse("package:" + PackagesListActivity.this.getPackageName()));
        intent_permissions.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent_permissions.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent_permissions.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        PackagesListActivity.this.startActivity(intent_permissions);
    }
}
