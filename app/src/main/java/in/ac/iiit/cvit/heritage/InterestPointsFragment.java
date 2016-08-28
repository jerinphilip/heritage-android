package in.ac.iiit.cvit.heritage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(
                new RecyclerViewOnItemClickListener(getActivity(), new RecyclerViewOnItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForLayoutPosition(position);
                        TextView textView = (TextView) viewHolder.itemView.findViewById(R.id.cardview_text);
                        String text = textView.getText().toString();

                        Intent intent_interest_point = new Intent(getActivity(), InterestPointActivity.class);
                        intent_interest_point.putExtra("interest_point", text);
                        startActivity(intent_interest_point);
                    }
                })
        );

/*
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildLayoutPosition(v);
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForLayoutPosition(position);

                TextView textView = (TextView) viewHolder.itemView.findViewById(R.id.cardview_text);
                String text = textView.getText().toString();

                Intent intent_interest_point = new Intent(getActivity(), InterestPointActivity.class);
                intent_interest_point.putExtra("interest_point", text);
                startActivity(intent_interest_point);
            }
        });
*/

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
