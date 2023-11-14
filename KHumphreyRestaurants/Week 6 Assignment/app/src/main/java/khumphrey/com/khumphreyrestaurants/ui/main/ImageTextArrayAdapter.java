package khumphrey.com.khumphreyrestaurants.ui.main;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import khumphrey.com.khumphreyrestaurants.R;

public class ImageTextArrayAdapter extends ArrayAdapter<Restaurant> {
    private final static String TAG = "ImageTextArrayAdapter";
    private int layoutResourceId;
    private Context context;
    private List<Restaurant>data;

    public ImageTextArrayAdapter(Context context, int layoutResourceId, List<Restaurant> data) {
        super(context, layoutResourceId,data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Log.d(TAG, "getView: rowView null: position "+ position);

        View rowView = inflater.inflate(layoutResourceId,parent,false);

        TextView restaurantNameView = rowView.findViewById(R.id.restaurant_name);
        TextView restaurantLocationView = rowView.findViewById(R.id.restaurant_location);
        TextView restaurantTypeView = rowView.findViewById(R.id.restaurant_type);
        TextView restuarantCostView = rowView.findViewById(R.id.restaurant_cost);
        ImageView restaurantIconView = rowView.findViewById(R.id.restaurant_icon);

        restaurantNameView.setText(data.get(position).name);
        restaurantLocationView.setText(data.get(position).location);
        restaurantTypeView.setText(data.get(position).type);
        restuarantCostView.setText(data.get(position).cost);
        restaurantIconView.setImageResource(data.get(position).icon);

        return rowView;
    }



}
