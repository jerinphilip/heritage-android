package in.ac.iiit.cvit.heritage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

public class InterestPointsFragment extends Fragment {

    private static final String LOGTAG = "Heritage";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_interest_points, container, false);

        String packageName = getActivity().getIntent().getStringExtra("package");
        Log.i(LOGTAG, packageName);

        ArrayList<InterestPoint> interestPoints = LoadPackage(packageName);

        GridView gridview_interest_points = (GridView) root.findViewById(R.id.gridview_interest_points);
        gridview_interest_points.setAdapter(new GridViewAdapter(getActivity(), R.layout.fragment_interest_points, interestPoints));

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
