package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.databases.DatabaseHelper;
import com.example.sqlite.entities.Category;

public class AddCategoryActivity extends AppCompatActivity {

    private EditText editTextName;
    private Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        setTitle(R.string.add_category);
        initView();
    }

    private void initView(){
        editTextName = findViewById(R.id.editTextName);

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
        Category category = new Category();
        category.setName(editTextName.getText().toString());
        if(databaseHelper.createCategory(category)){
            Intent intent = new Intent(AddCategoryActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnCancel_onClick(View view) {
        Intent intent = new Intent(AddCategoryActivity.this, MainActivity.class);
        startActivity(intent);
    }
}