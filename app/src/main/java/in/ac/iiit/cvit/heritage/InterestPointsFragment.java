package in.ac.iiit.cvit.heritage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class InterestPointsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;

    private static final String LOGTAG = "Heritage";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_interest_points, container, false);

        String packageName = getActivity().getIntent().getStringExtra("package");
        Log.i(LOGTAG, packageName);

        ArrayList<InterestPoint> interestPoints = LoadPackage(packageName);

/*
        final GridView gridview_interest_points = (GridView) root.findViewById(R.id.gridview_interest_points);
        final GridViewAdapter gridViewAdapter = new GridViewAdapter(getActivity(), R.layout.fragment_interest_points, interestPoints);
        gridview_interest_points.setAdapter(gridViewAdapter);
        gridview_interest_points.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InterestPoint interestPoint = gridViewAdapter.getItem(position);
                interestPoint.get("title");
            }
        });
*/

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_interest_points);
        recyclerView.setHasFixedSize(true);
        recyclerViewLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(interestPoints);
        recyclerView.setAdapter(recyclerViewAdapter);

        return root;
    }

    private ArrayList<InterestPoint> LoadPackage(String packageName){
        PackageReader reader;
        packageName = packageName.toLowerCase();
        reader = new PackageReader(packageName);
        ArrayList<InterestPoint> interestPoints = reader.getContents();
        return interestPoints;
    }
}
