package khumphrey.com.khumphrey_midterm.ui.main;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import khumphrey.com.khumphrey_midterm.R;
import khumphrey.com.khumphrey_midterm.SecondViewActivity;

public class FirstViewFragment extends Fragment {

    private FirstViewModel mViewModel;
    public static final String TAG = "FirstViewFragment";
    TextView likeTv;
    private ActivityResultLauncher<Intent> secondActivityResultLauncher;

    public static FirstViewFragment newInstance() {
        return new FirstViewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FirstViewModel.class);
        // TODO: Use the ViewModel

        secondActivityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        this::secondOnActivityResult
                );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        likeTv = getActivity().findViewById(R.id.fir_iLike);
        likeTv.setVisibility(View.INVISIBLE);

        Button detailsButton = getActivity().findViewById(R.id.fir_detailsButton);
        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetails();
            }
        });
    }

    void showDetails(){
        Intent intent = new Intent(getActivity(), SecondViewActivity.class);    // If issues running, look here first.
        intent.putExtra(SecondViewFragment.NAME, "Android's Really Long Tablet name");
        intent.putExtra(SecondViewFragment.OSVERSION, "Tiramisu (OS 13)");
        intent.putExtra(SecondViewFragment.DIMENSIONS, "9.6x7.5in");
        intent.putExtra(SecondViewFragment.WEIGHT, "16oz");
        intent.putExtra(SecondViewFragment.IMAGE, "R.drawable.tablet_2nd.jpg");

        secondActivityResultLauncher.launch(intent);

    }

    public void secondOnActivityResult(ActivityResult result) {
        boolean like = false;
        if (result.getResultCode() == Activity.RESULT_OK) {
            if(result.getData().hasExtra(SecondViewFragment.OPINION)) {
                like = result.getData().getBooleanExtra(SecondViewFragment.OPINION, false);
                Log.d(TAG, " secondActivityResult RESULT_OK second page like: "+like);
                if (like) {
                    likeTv.setVisibility(View.VISIBLE);
                }
                else {
                    likeTv.setVisibility(View.INVISIBLE);
                }
            }
            else {
                Log.d(TAG, "secondOnActivityResult result is not RESULT_NOT_OK");
            }
        }

    }

}