package khumphrey.com.khumphreyrestaurants.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import khumphrey.com.khumphreyrestaurants.R;

public class RestaurantListFragment extends Fragment {

    private RestaurantListViewModel mViewModel;
    private List<Restaurant> restaurant_data;
    private int mPosition;

    public static RestaurantListFragment newInstance() {
        return new RestaurantListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RestaurantListViewModel.class);
        // TODO: Use the ViewModel
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restaurant_list, container, false);
    }

    private List<Restaurant> setupRestaurants() {

        Restaurant[] restaurants = new Restaurant[] {
                new Restaurant(R.drawable.hillstone, getString(R.string.name_hillstone), getString(R.string.location_hillstone), getString(R.string.type_hillstone), getString(R.string.cost_hillstone), getString(R.string.rec_hillstone)),
                new Restaurant(R.drawable.esperanza, getString(R.string.name_esperanza), getString(R.string.location_esperanza), getString(R.string.type_esperanza), getString(R.string.cost_esperanza), getString(R.string.rec_esperanza)),
                new Restaurant(R.drawable.la_villa_restaurant, getString(R.string.name_villa), getString(R.string.location_villa), getString(R.string.type_villa), getString(R.string.cost_villa), getString(R.string.rec_villa)),
                new Restaurant(R.drawable.simmzys, getString(R.string.name_simmzys), getString(R.string.location_simmzys), getString(R.string.type_simmzys), getString(R.string.cost_simmzys), getString(R.string.rec_simmzys)),
                new Restaurant(R.drawable.crazy_rock_n_sushi, getString(R.string.name_crazy_rock), getString(R.string.location_crazy_rock), getString(R.string.type_crazy_rock), getString(R.string.cost_crazy_rock), getString(R.string.rec_crazy_rock))
        };

        List<Restaurant> restaurant_data_list = new ArrayList<>(Arrays.asList(restaurants));

        return restaurant_data_list;
    }

    @Override
    public void onViewCreated(@NonNull View view,@NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        restaurant_data = setupRestaurants();
        ListView listView = getActivity().findViewById(R.id.restaurantList);

        ImageTextArrayAdapter adapter = new ImageTextArrayAdapter(getActivity(), R.layout.restaurant_item,restaurant_data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long arg3) {
                clickListItem(view, position);
            }
        });

        Button recButton = getActivity().findViewById(R.id.recButton);
        recButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                clickRecommendationButton();
            }
        });
    }

    private void clickListItem(View view, int position) {
        String item =
                ((TextView)view.findViewById(R.id.restaurant_name)).getText().toString();
        Toast.makeText(getActivity(), item, Toast.LENGTH_LONG).show();
        mPosition = position;
    }

    private void clickRecommendationButton() {
        String message = restaurant_data.get(mPosition).recommendation;
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}