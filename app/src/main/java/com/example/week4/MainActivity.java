package com.example.week4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText taskEditText;
    private Switch urgentSwitch;
    private Button addButton;
    private ListView taskListView;

    private ArrayList<TodoItem> todoList;
    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link UI components
        taskEditText = findViewById(R.id.taskEditText);
        urgentSwitch = findViewById(R.id.urgentSwitch);
        addButton = findViewById(R.id.addButton);
        taskListView = findViewById(R.id.taskListView);

        // Initialize list and adapter
        todoList = new ArrayList<>();
        adapter = new TodoAdapter(this, todoList);
        taskListView.setAdapter(adapter);

        // Add button click
        addButton.setOnClickListener(v -> {
            String taskText = taskEditText.getText().toString().trim();
            boolean isUrgent = urgentSwitch.isChecked();

            if (!taskText.isEmpty()) {
                TodoItem newItem = new TodoItem(taskText, isUrgent);
                todoList.add(newItem);
                adapter.notifyDataSetChanged();

                taskEditText.setText("");
                urgentSwitch.setChecked(false);
            } else {
                Toast.makeText(this, "Please enter a task.", Toast.LENGTH_SHORT).show();
            }
        });

        // Long click to delete
        taskListView.setOnItemLongClickListener((parent, view, position, id) -> {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Do you want to delete this?")
                    .setMessage("The selected row is: " + position)
                    .setPositiveButton("Yes", (dialog, which) -> {
                        todoList.remove(position);
                        adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        });
    }
}
