package com.example.sqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sqlite.R;
import com.example.sqlite.entities.Category;
import com.example.sqlite.entities.Computer;

import java.util.List;

public class ComputerAdapter extends ArrayAdapter<Computer> {

    private Context context;
    private int layout;
    private List<Computer> computers;


    public ComputerAdapter(@NonNull Context context, int resource, @NonNull List<Computer> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.computers = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       Computer computer = computers.get(position);
       View view = LayoutInflater.from(context).inflate(layout, null);
       TextView textViewName = view.findViewById(R.id.textViewName);
       textViewName.setText(computer.getName());
        TextView textViewCategory = view.findViewById(R.id.textViewCategory);
        textViewName.setText(String.valueOf(computer.getCategoryId()));
        return view;
    }

}
