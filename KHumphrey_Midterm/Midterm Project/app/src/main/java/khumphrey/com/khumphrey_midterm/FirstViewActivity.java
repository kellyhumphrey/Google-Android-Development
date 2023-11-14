package khumphrey.com.khumphrey_midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import khumphrey.com.khumphrey_midterm.ui.main.FirstViewFragment;

public class FirstViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_view);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FirstViewFragment.newInstance())
                    .commitNow();
        }
    }
}