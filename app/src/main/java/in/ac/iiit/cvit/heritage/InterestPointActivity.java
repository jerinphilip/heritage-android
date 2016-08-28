package in.ac.iiit.cvit.heritage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class InterestPointActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private static final String LOGTAG = "Heritage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_point);

        Intent intent = getIntent();
        String text_interest_point = intent.getStringExtra("interest_point");
        Log.i(LOGTAG, text_interest_point);

        toolbar = (Toolbar) findViewById(R.id.coordinatorlayout_toolbar);
        toolbar.setTitle(text_interest_point);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.coordinatorlayout_colltoolbar);
        collapsingToolbarLayout.setTitle(text_interest_point);
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(InterestPointActivity.this, R.color.colorPrimaryDark));
    }
}
