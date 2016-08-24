package in.ac.iiit.cvit.heritage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PackageDownloaderActivity extends AppCompatActivity {

    private ListView listview_available_packages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_downloader);

        //temporary hard coding
        String[] packages = {"Golconda"};
        listview_available_packages = (ListView) findViewById(R.id.listview_available_packages);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(PackageDownloaderActivity.this, android.R.layout.simple_list_item_1, packages);
        listview_available_packages.setAdapter(adapter);
    }
}
