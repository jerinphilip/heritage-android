package in.ac.iiit.cvit.heritage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class InterestPointsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_interest_points, container, false);

        GridView gridview_interest_points = (GridView) root.findViewById(R.id.gridview_interest_points);
        //gridview_interest_points.setAdapter(new GridViewAdapter(getActivity(), interestPoints));

        return root;
    }
}
