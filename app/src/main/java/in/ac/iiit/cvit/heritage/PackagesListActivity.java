package in.ac.iiit.cvit.heritage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class PackagesListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button button_download_packages;
    private ListView listview_package_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_lists);

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
        String[] packages = {"Golconda"};
        listview_package_list = (ListView) findViewById(R.id.listview_package_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(PackagesListActivity.this, android.R.layout.simple_list_item_1, packages);
        listview_package_list.setAdapter(adapter);
    }
}
