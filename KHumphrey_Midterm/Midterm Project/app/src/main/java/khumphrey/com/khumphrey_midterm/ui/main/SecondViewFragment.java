package khumphrey.com.khumphrey_midterm.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import khumphrey.com.khumphrey_midterm.R;

public class SecondViewFragment extends Fragment {

    private SecondViewModel mViewModel;
    public static String NAME = "name";
    public static String OSVERSION = "osversion";
    public static String DIMENSIONS = "dimensions";
    public static String WEIGHT = "weight";
    public static String IMAGE = "image";
    public static String OPINION = "opinion";


    public static SecondViewFragment newInstance() {
        return new SecondViewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SecondViewModel.class);
        // TODO: Use the ViewModel
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        TextView tvName = getActivity().findViewById(R.id.sec_name);
        TextView tvOSVersion = getActivity().findViewById(R.id.sec_os_version);
        TextView tvDimensions = getActivity().findViewById(R.id.sec_dimensions);
        TextView tvWeight = getActivity().findViewById(R.id.sec_weight);
        ImageView ivImage = getActivity().findViewById(R.id.sec_ImageView);

        final CheckBox likeTablet = getActivity().findViewById(R.id.sec_TabletCheckBox);

        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!= null) {
            String name = bundle.getString(NAME);
            String osversion = bundle.getString(OSVERSION);
            String dimensions = bundle.getString(DIMENSIONS);
            String weight = bundle.getString(WEIGHT);
            int image_id = bundle.getInt(IMAGE, -1);

            tvName.setText(!TextUtils.isEmpty(name) ? name : "Unknown");
            tvOSVersion.setText(!TextUtils.isEmpty(osversion) ? osversion : "Unknown");
            tvDimensions.setText(!TextUtils.isEmpty(dimensions) ? dimensions : "Unknown");
            tvWeight.setText(!TextUtils.isEmpty(weight) ? weight : "Unknown");
            if(image_id > -1){
                ivImage.setImageResource(image_id);
                ivImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }

            ivImage.setBackgroundResource(0);
        }

        Button b = getActivity().findViewById(R.id.sec_return_button);
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                boolean like = likeTablet.isChecked();
                Intent data = new Intent();
                data.putExtra(OPINION, like);
                getActivity().setResult(Activity.RESULT_OK, data);
                getActivity().finish();
            }
        });
    }
}