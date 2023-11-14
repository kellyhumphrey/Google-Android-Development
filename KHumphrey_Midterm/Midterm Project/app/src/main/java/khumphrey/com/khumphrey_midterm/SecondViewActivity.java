package khumphrey.com.khumphrey_midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import khumphrey.com.khumphrey_midterm.ui.main.SecondViewFragment;

public class SecondViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_view);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SecondViewFragment.newInstance())
                    .commitNow();
        }
    }
}