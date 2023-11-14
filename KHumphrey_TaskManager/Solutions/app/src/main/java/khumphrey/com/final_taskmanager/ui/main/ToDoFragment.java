package khumphrey.com.final_taskmanager.ui.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import khumphrey.com.final_taskmanager.R;

public class ToDoFragment extends Fragment
        implements TaskRecyclerViewAdapter.OnAdapterItemInteraction {

    private ToDoViewModel mViewModel;
    public static String TAG ="To Do List";
    TaskRecyclerViewAdapter adapter;
    public static ToDoFragment newInstance() {
        return new ToDoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ToDoViewModel.class);
        // TODO: Use the ViewModel
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo, container, false);
    }

    @Override
    public void onItemSelected(Task task) {
        Toast.makeText(getActivity(), task.getAdditionalInformation(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(Task task) {onDelete(task);}

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.init_database(getActivity());

        // Instantiate the recyclerView
        RecyclerView recyclerView =
                getActivity().findViewById(R.id.tasksView);
        // Instantiate the layoutManager and set it into the recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Instantiate an AnimalRecyclerViewAdapter, pass the data from the viewModel & the object
        adapter=new TaskRecyclerViewAdapter(mViewModel.getTasks(), this);
        // Set the adapter into the recyclerView
        recyclerView.setAdapter(adapter);
        // Instantiate the save button and attach a listener
        Button saveButton = getActivity().findViewById(R.id.addButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {onSave();}
        });
    }

    @SuppressLint("NonConstantResourceId")
    private void onSave() {
        EditText Title = getActivity().findViewById(R.id.enterTaskTitle);
        String taskTitle = Title.getText().toString();

        EditText Date = getActivity().findViewById(R.id.enterDueDate);
        String dueDate = Date.getText().toString();

        EditText Description = getActivity().findViewById(R.id.enterShortDescription);
        String shortDescription = Description.getText().toString();

        EditText Information = getActivity().findViewById(R.id.enterAdditionalInformation);
        String additionalInformation = Information.getText().toString();


        // Don’t enter info which does not contain mandatory items
        if (TextUtils.isEmpty(taskTitle)) {
            showMissingTaskTitleAlert();
        }
        else if (TextUtils.isEmpty(dueDate)) {
            showMissingDueDateAlert();
        }
        else if (!DateCheck(dueDate))
        {
            showDateFormatAlert();
        }
        else {
            if (TextUtils.isEmpty(additionalInformation)) {additionalInformation = "No additional information was provided.";}

            mViewModel.addTask(taskTitle, dueDate, shortDescription, additionalInformation);

            // Notifies the adapter that the underlying data has changed,
            // any View reflecting the data should refresh itself.
            adapter.notifyDataSetChanged();


            // Remove the soft keyboard after hitting the save button
            InputMethodManager inputManager = (InputMethodManager)
                    getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().
                    getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public boolean DateCheck(String myDate) {
        List<String> dateFormats = new ArrayList<>();
        dateFormats.add("dd/MM/yyyy");
        dateFormats.add("MM/dd/yyyy");
        dateFormats.add("yyyy/MM/dd");
        dateFormats.add("yyyy-MM-dd");
        dateFormats.add("dd-MM-yyyy");
        dateFormats.add("MM-dd-yyyy");

        // Add more formats as needed

        LocalDate date = null;
        boolean isValid = false;

        // Try parsing the date using each format
        for (String format : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                date = LocalDate.parse(myDate, formatter);
                isValid = true;
                break; // If parsing is successful, no need to check other formats
            } catch (Exception e) {
                // Parsing failed for this format, continue with the next one
            }
        }
        return isValid;
    }


    private void onDelete(Task task){
        // When clicked, delete the item that was clicked.
        // (Show a toast to indicate what is occurring)
        if (task != null) {
            String item = "deleting: " + task.getTaskTitle();
            Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
            Log.d(TAG, " onItemClick: " + task.getTaskTitle());
            // Removes the object from the array held in the viewModel
            mViewModel.removeTask(task);
            // Notifies that the underlying data has changed
            adapter.notifyDataSetChanged();
        }
    }

    public void showMissingTaskTitleAlert() {
        ContextThemeWrapper ctw = new ContextThemeWrapper(getActivity(), R.style.AlertDialogCustom );

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
        alertDialogBuilder.setTitle(getResources().getString(R.string.alert_title_taskTitle));
        alertDialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        // set dialog message
        alertDialogBuilder
                .setMessage(getResources().getString(R.string.alert_message_taskTitle))
                .setCancelable(false)
                .setPositiveButton("Okie Dokie",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    public void showMissingDueDateAlert() {
        ContextThemeWrapper ctw = new ContextThemeWrapper(getActivity(), R.style.AlertDialogCustom );

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
        alertDialogBuilder.setTitle(getResources().getString(R.string.alert_title_dueDate));
        alertDialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        // set dialog message
        alertDialogBuilder
                .setMessage(getResources().getString(R.string.alert_message_dueDate))
                .setCancelable(false)
                .setPositiveButton("Okie Dokie",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    public void showDateFormatAlert() {
        ContextThemeWrapper ctw = new ContextThemeWrapper(getActivity(), R.style.AlertDialogCustom );

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
        alertDialogBuilder.setTitle(getResources().getString(R.string.alert_title_dueDateFormat));
        alertDialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        // set dialog message
        alertDialogBuilder
                .setMessage(getResources().getString(R.string.alert_message_dueDateFormat))
                .setCancelable(false)
                .setPositiveButton("Okie Dokie",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }
}
