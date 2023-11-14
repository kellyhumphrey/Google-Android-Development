package khumphrey.com.final_taskmanager.ui.main;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import khumphrey.com.final_taskmanager.R;

public class TaskRecyclerViewAdapter extends
        RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder>{

    public final static String TAG ="TaskListAdapter";
    private List<Task> mValues;
    OnAdapterItemInteraction mListener;

    public TaskRecyclerViewAdapter(List<Task> items, OnAdapterItemInteraction listener) {
        mValues = items;
        mListener = listener;
    }


    @NonNull
    @Override
    public TaskRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row_linear_version_2, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtTaskTitle;
        public final TextView txtShortDescription;
        public final TextView txtDueDate;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtTaskTitle = view.findViewById(R.id.taskTitle);
            txtShortDescription = view.findViewById(R.id.shortDescription);
            txtDueDate = view.findViewById(R.id.dueDate);
        }
        @Override
        public String toString() {return super.toString() + " '" + txtTaskTitle.getText() + "'";}
    }
    public interface OnAdapterItemInteraction {
        void onItemSelected(Task task);
        void onItemLongClick(Task task);
    }

    public void add(Task item) {
        Log.d(TAG, "Add" + item.toString());
        mValues.add(item);
        notifyItemInserted(mValues.size()-1);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.txtTaskTitle.setText(mValues.get(position).getTaskTitle());
        holder.txtShortDescription.setText(mValues.get(position).getShortDescription());
        holder.txtDueDate.setText(mValues.get(position).getDueDate());

        final Task task = mValues.get(position);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mListener) {
                    mListener.onItemSelected(task);
                }
            }
        });
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View	v) {
                if (null != mListener) {
                    mListener.onItemLongClick(task);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {return mValues.size();}


}
