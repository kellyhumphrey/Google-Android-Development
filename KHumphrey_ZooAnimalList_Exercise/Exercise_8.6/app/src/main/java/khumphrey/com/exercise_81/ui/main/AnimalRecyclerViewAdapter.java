package khumphrey.com.exercise_81.ui.main;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import khumphrey.com.exercise_81.R;

public class AnimalRecyclerViewAdapter extends
        RecyclerView.Adapter<AnimalRecyclerViewAdapter.ViewHolder>{

    public final static String TAG = "ZooListAdapter";
    private List<Animal> mValues;
    OnAdapterItemInteraction mListener;

    public AnimalRecyclerViewAdapter(List<Animal> items, OnAdapterItemInteraction listener) {
        mValues = items;
        mListener = listener;
    }

    @NonNull
    @Override
    public AnimalRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_linear, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.txtName.setText(mValues.get(position).getName());
        holder.txtLocation.setText(mValues.get(position).getLocation());
        if (TextUtils.equals(mValues.get(position).getType(),Animal.MAMMAL)) {holder.imgSpecies.setImageResource(R.drawable.ic_lion);}
        else if (TextUtils.equals(mValues.get(position).getType(),Animal.BIRD)) {holder.imgSpecies.setImageResource(R.drawable.ic_bird);}
        else {holder.imgSpecies.setImageResource(R.drawable.ic_lizard);}

        final Animal animal = mValues.get(position);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onItemSelected(animal);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imgSpecies;
        public final TextView txtName;
        public final TextView txtLocation;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtName = view.findViewById(R.id.name);
            txtLocation = view.findViewById(R.id.location);
            imgSpecies = view.findViewById(R.id.icon);
        }
        @Override
        public String toString() {return super.toString() + " '" + txtLocation.getText() + "'";}
    }

    public interface OnAdapterItemInteraction {
        void onItemSelected(Animal animal);
    }

    public void add(Animal item){
        Log.d(TAG,"Add " + item.toString());
        mValues.add(item);
        notifyItemInserted(mValues.size()-1);
    }




    /*
    Method to directly remove Animal object data directly from adapter

    public void remove(Animal item) {
     int position = mValues.indexOf(item);
     mValues.remove(position);
     notifyItemRemoved(position);
    }
     */
}
