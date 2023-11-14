package khumphrey.com.final_taskmanager.ui.main;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ToDoViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public final static String TAG = "ToDoViewModel";
    private DBHelper dbHelper = null;
    List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {return tasks;}

    public Task addTask(String taskTitle, String dueDate, String shortDescription, String additionalInformation)
    {
        Task myTask = new Task();
        myTask.setTaskTitle(taskTitle);
        myTask.setDueDate(dueDate);
        myTask.setShortDescription(shortDescription);
        myTask.setAdditionalInformation(additionalInformation);

        long taskId;
        if (dbHelper != null) {
            taskId = dbHelper.insert(myTask);
            myTask.setId(taskId);
        }
        return myTask;
    }

    public Task removeTask(Task task) {
        tasks.remove(task);

        if (dbHelper != null) {
            dbHelper.deleteRecord(task.getId());
        }
        return task;
    }

    public void init_database(Context context) {
        try {
            if(dbHelper == null) {
                Log.d(TAG, "init_database: DBHelper is null. Create one. ");
                dbHelper = new DBHelper(context);
                tasks = dbHelper.selectAll();
            }
            else {
                Log.d(TAG, "init_database: DBHelper already exists");
            }
            if (!tasks.isEmpty()){
                Log.d(TAG," animals list is not empty size: " + tasks.size());
            }
        }
        catch (Exception e) {
            Log.d(TAG, "init_database: DBHelper threw exception : " + e);
            e.printStackTrace();
        }
    }
}