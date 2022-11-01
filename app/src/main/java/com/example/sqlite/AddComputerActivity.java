package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sqlite.adapter.CategoryAdapter;
import com.example.sqlite.databases.DatabaseHelper;
import com.example.sqlite.entities.Category;
import com.example.sqlite.entities.Computer;

import java.util.List;

public class AddComputerActivity extends AppCompatActivity {

    private EditText editTextName;
    private Spinner spinnerCategory;
    private Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_computer);
        setTitle(R.string.add_computer);
        initView();
        loadData();
    }

    private void loadData(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<Category> categories = databaseHelper.findAllCategories();
        if(!categories.isEmpty()) {
            spinnerCategory.setAdapter(new CategoryAdapter(getApplicationContext(), R.layout.category_layout, categories));
        }
    }

    private void initView(){
        editTextName = findViewById(R.id.editTextName);

        spinnerCategory = findViewById(R.id.spinnerCategory);

        btnSave = findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSave_onClick(view);
            }
        });
        btnCancel = findViewById(R.id.buttonCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCancel_onClick(view);
            }
        });
    }

    public void btnSave_onClick(View view) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        Computer computer = new Computer();
        computer.setName(editTextName.getText().toString());
        Category category = (Category) spinnerCategory.getSelectedItem();
        computer.setCategoryId(category.getId());
        if(databaseHelper.createComputer(computer)){
            Intent intent = new Intent(AddComputerActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnCancel_onClick(View view) {
        Intent intent = new Intent(AddComputerActivity.this, MainActivity.class);
        startActivity(intent);
    }
}