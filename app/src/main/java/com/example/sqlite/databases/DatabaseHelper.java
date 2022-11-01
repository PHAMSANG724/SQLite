package com.example.sqlite.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqlite.entities.Category;
import com.example.sqlite.entities.Computer;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String dbName = "computerDB";
    private static int dbVersion = 1;

    private static String computerTable = "computer";
    private static String categoryTable = "category";

    private static String idColumn = "id";
    private static String nameColumn = "name";
    private static String categoryIdColumn = "categoryId";


    public DatabaseHelper(@Nullable Context context){
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + categoryTable + "(" +
                idColumn + " integer primary key autoincrement, " +
                nameColumn + " text" +
                ")");

        sqLiteDatabase.execSQL(" create table " + computerTable + "(" +
                idColumn + " integer primary key autoincrement, " +
                nameColumn + " text, " +
                categoryIdColumn + " integer references " + categoryTable + "(" + idColumn + ")" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean createCategory(Category category) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(nameColumn, category.getName());
            return sqLiteDatabase.insert(categoryTable, null, contentValues) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createComputer(Computer computer) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(nameColumn, computer.getName());
            contentValues.put(categoryIdColumn, computer.getCategoryId());
            return sqLiteDatabase.insert(computerTable, null, contentValues) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Category> findAllCategories() {
        List<Category> categories = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(" select * from " + categoryTable, null);
            if (cursor.moveToFirst()) {
                categories = new ArrayList<Category>();
                do {
                    Category category = new Category();
                    category.setId(cursor.getInt(0));
                    category.setName(cursor.getString(1));
                    categories.add(category);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            categories = null;
        }
        return categories;
    }

    public List<Computer> findAllComputers() {
        List<Computer> computers = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(" select * from " + categoryTable, null);
            if (cursor.moveToFirst()) {
                computers = new ArrayList<Computer>();
                do {
                    Computer computer = new Computer();
                    computer.setId(cursor.getInt(0));
                    computer.setName(cursor.getString(1));
                    computer.setCategoryId(cursor.getInt(2));
                    computers.add(computer);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            computers = null;
        }
        return computers;
    }
}
