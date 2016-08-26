package in.ac.iiit.cvit.heritage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter<InterestPoint> {

    public GridViewAdapter(Context context, ArrayList<InterestPoint> interestPoints) {
        super(context, R.layout.item_interest_point, interestPoints);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view_item_interest_point = inflater.inflate(R.layout.item_interest_point, parent, false);

        InterestPoint interestPoint = getItem(position);
        ImageView imageview_interest_point = (ImageView) view_item_interest_point.findViewById(R.id.imageview_interest_point_image);
        TextView textview_interest_point = (TextView) view_item_interest_point.findViewById(R.id.textview_interest_point_name);

        return view_item_interest_point;
    }
}
