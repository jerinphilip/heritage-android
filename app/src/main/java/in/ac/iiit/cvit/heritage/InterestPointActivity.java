package in.ac.iiit.cvit.heritage;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class InterestPointActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_point);

        toolbar = (Toolbar) findViewById(R.id.coordinatorlayout_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.coordinatorlayout_colltoolbar);
        collapsingToolbarLayout.setTitle("Interest Point");
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(InterestPointActivity.this, R.color.colorPrimaryDark));
    }
}
