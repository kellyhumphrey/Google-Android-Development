package khumphrey.com.khumphrey_restaurantlist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import khumphrey.com.khumphrey_restaurantlist.ui.main.RestaurantListFragment;

public class RestaurantListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, RestaurantListFragment.newInstance())
                .commitNow();
        }
    }
}