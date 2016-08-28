package in.ac.iiit.cvit.heritage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataObjectHolder> {

    private ArrayList<InterestPoint> interestPoints;
    private static CardOnClickListener cardOnClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView;

        public DataObjectHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.cardview_image);
            textView = (TextView) view.findViewById(R.id.cardview_text);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            cardOnClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(CardOnClickListener cardOnClickListener) {
        this.cardOnClickListener = cardOnClickListener;
    }

    public RecyclerViewAdapter(ArrayList<InterestPoint> interestPoints) {
        this.interestPoints = interestPoints;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_image_text, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.textView.setText(interestPoints.get(position).get("title"));
    }

    public void addItem(InterestPoint interestPoint, int index) {
        interestPoints.add(index, interestPoint);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        interestPoints.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return interestPoints.size();
    }

    public interface CardOnClickListener {
        void onItemClick(int position, View v);
    }
}
