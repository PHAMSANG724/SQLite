package com.example.sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.sqlite.adapter.ComputerAdapter;
import com.example.sqlite.databases.DatabaseHelper;
import com.example.sqlite.entities.Computer;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewComputer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadData();
        
    }

    private void initView() {
        listViewComputer = findViewById(R.id.listviewComputer);
    }

    private void loadData() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<Computer> computers = databaseHelper.findAllComputers();
        if(!computers.isEmpty()){
            listViewComputer.setAdapter(new ComputerAdapter(getApplicationContext(), R.layout.computer_layout, computers));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_add_category){
            menuAddCategorySelected(item);
        }else if (item.getItemId() == R.id.menu_add_computer){
            menuAddComputerSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void menuAddCategorySelected(MenuItem item){
        Intent intent = new Intent(MainActivity.this, AddCategoryActivity.class);
        startActivity(intent);
    }

    public void menuAddComputerSelected(MenuItem item){
        Intent intent = new Intent(MainActivity.this, AddComputerActivity.class);
        startActivity(intent);
    }
}