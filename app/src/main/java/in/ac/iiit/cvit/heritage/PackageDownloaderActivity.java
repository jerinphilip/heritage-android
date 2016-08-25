package in.ac.iiit.cvit.heritage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PackageDownloaderActivity extends AppCompatActivity {

    private ListView listview_available_packages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_downloader);

        //temporary hard coding
        String[] packages = {"golconda"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(PackageDownloaderActivity.this, android.R.layout.simple_list_item_1, packages);

        listview_available_packages = (ListView) findViewById(R.id.listview_available_packages);
        listview_available_packages.setAdapter(adapter);
        listview_available_packages.setClickable(true);
        listview_available_packages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String list_item = adapter.getItem(position).toLowerCase();
                new PackageDownloader(PackageDownloaderActivity.this).execute(list_item);
            }
        });
    }
}
