package khumphrey.com.khumphrey_restaurantlist.ui.main;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import khumphrey.com.khumphrey_restaurantlist.R;

public class RestaurantListFragment extends Fragment
        implements RestaurantRecyclerAdapter.OnAdapterItemInteraction{

    private RestaurantListViewModel myViewModel;
    private int mPosition;
    private List<Restaurant> restaurant_data;
    RestaurantRecyclerAdapter restaurantRecyclerAdapter;
    public static RestaurantListFragment newInstance() {
        return new RestaurantListFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myViewModel = new ViewModelProvider(this).get(RestaurantListViewModel.class);
        // TODO: Use the ViewModel
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restaurant_list, container, false);
    }

    @Override
    public void onItemSelected(Restaurant restaurant, Integer position)
    {
        String item = restaurant.getName();
        Toast.makeText(getActivity(), item, Toast.LENGTH_LONG).show();
        mPosition = position;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        Button recButton = getActivity().findViewById(R.id.recButton);
        recButton.setOnClickListener(new View.OnClickListener(){public void onClick(View view){clickRecButton();} });

        restaurant_data = setupRestaurants();

        RecyclerView recyclerView = getActivity().findViewById(R.id.restaurantRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        restaurantRecyclerAdapter = new RestaurantRecyclerAdapter(restaurant_data, this);
        recyclerView.setAdapter(restaurantRecyclerAdapter);
    }

    private void clickRecButton()
    {
        String message = restaurant_data.get(mPosition).recommendation + "\n"
                + "A fun fact is: " + restaurant_data.get(mPosition).funFact;
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    private List<Restaurant> setupRestaurants() {
        List<Restaurant> restaurant_data_list;


        Restaurant[] restaurants = new Restaurant[] {
                new Restaurant(R.drawable.hillstone, getString(R.string.name_hillstone), getString(R.string.location_hillstone), getString(R.string.type_hillstone), getString(R.string.cost_hillstone), getString(R.string.rec_hillstone), getString(R.string.funFact_hillstone)),
                new Restaurant(R.drawable.esperanza, getString(R.string.name_esperanza), getString(R.string.location_esperanza), getString(R.string.type_esperanza), getString(R.string.cost_esperanza), getString(R.string.rec_esperanza), getString(R.string.funFact_esperanza)),
                new Restaurant(R.drawable.simmzys, getString(R.string.name_simmzys), getString(R.string.location_simmzys), getString(R.string.type_simmzys), getString(R.string.cost_simmzys), getString(R.string.rec_simmzys), getString(R.string.funFact_simmzys)),
                new Restaurant(R.drawable.la_villa_restaurant, getString(R.string.name_villa), getString(R.string.location_villa), getString(R.string.type_villa), getString(R.string.cost_villa), getString(R.string.rec_villa), getString(R.string.funFact_villa)),
                new Restaurant(R.drawable.crazy_rock_n_sushi, getString(R.string.name_crazy_rock), getString(R.string.location_crazy_rock), getString(R.string.type_crazy_rock), getString(R.string.cost_crazy_rock), getString(R.string.rec_crazy_rock), getString(R.string.funFact_crazy_rock))
        };

        restaurant_data_list = new ArrayList<>(Arrays.asList(restaurants));
        return restaurant_data_list;
    }
}