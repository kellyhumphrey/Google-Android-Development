package khumphrey.com.khumphrey_restaurantlist.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import khumphrey.com.khumphrey_restaurantlist.R;

public class RestaurantRecyclerAdapter
        extends RecyclerView.Adapter <RestaurantRecyclerAdapter.ViewHolder>{

    View mView;
    ImageView restaurantIconIv;
    TextView restaurantNameTv;
    TextView restaurantLocationTv;
    TextView restaurantTypeTv;
    TextView restaurantCostTv;
    TextView restaurantRecTv;
    TextView restaurantFunFactTv;
    private List<Restaurant> mValues;
    private OnAdapterItemInteraction mListener;

    public RestaurantRecyclerAdapter(List<Restaurant> items, OnAdapterItemInteraction listener)
    {
        mValues = items;
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        ImageView restaurantIconIv;
        TextView restaurantNameTv;
        TextView restaurantLocationTv;
        TextView restaurantTypeTv;
        TextView restaurantCostTv;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            restaurantIconIv = view.findViewById(R.id.restaurant_icon);
            restaurantNameTv = view.findViewById(R.id.restaurant_name);
            restaurantLocationTv = view.findViewById(R.id.restaurant_location);
            restaurantTypeTv = view.findViewById(R.id.restaurant_type);
            restaurantCostTv = view.findViewById(R.id.restaurant_cost);
        }

        @Override
        public String toString() {return super.toString();}
    }


    public interface OnAdapterItemInteraction {
        void onItemSelected(Restaurant restaurant, Integer position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.restaurantNameTv.setText(mValues.get(position).getName());
        holder.restaurantLocationTv.setText(mValues.get(position).getLocation());
        holder.restaurantTypeTv.setText(mValues.get(position).getType());
        holder.restaurantCostTv.setText(mValues.get(position).getCost());
        holder.restaurantIconIv.setImageResource(mValues.get(position).getLogo());

        Restaurant selectedRestaurant = mValues.get(position);
        int pos = holder.getAdapterPosition();

        holder.mView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (null != mListener)
                {
                    mListener.onItemSelected(selectedRestaurant, pos);
                }
            }
        });
    }

    @Override
    public int getItemCount() {return mValues.size();}

    public void add(Restaurant item)
    {
        mValues.add(item);
        notifyItemInserted(mValues.size()-1);
    }
}
