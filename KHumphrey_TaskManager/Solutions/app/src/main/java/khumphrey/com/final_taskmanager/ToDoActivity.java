package khumphrey.com.final_taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import khumphrey.com.final_taskmanager.ui.main.ToDoFragment;

public class ToDoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ToDoFragment.newInstance())
                    .commitNow();
        }
    }
}