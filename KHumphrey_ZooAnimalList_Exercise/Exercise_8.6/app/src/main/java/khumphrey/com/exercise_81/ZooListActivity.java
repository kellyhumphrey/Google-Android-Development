package khumphrey.com.exercise_81;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import khumphrey.com.exercise_81.ui.main.ZooListFragment;

public class ZooListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_list);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ZooListFragment.newInstance())
                    .commitNow();
        }
    }
}