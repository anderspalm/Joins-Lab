package com.example.andeski.joins_lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 9;

    public static final String EMPLOYEE_TABLE = "employees";
    public static final String EMPLOYEE_SSN = "emp_SSN";
    public static final String EMPLOYEE_FIRST_NAME = "first";
    public static final String EMPLOYEE_SECOND_NAME = "last";
    public static final String EMPLOYEE_Y_O_B = "birthyear";
    public static final String EMPLOYEE_CITY = "city";

    public static final String JOB_TABLE = "jobs";
    public static final String JOB_SSN = "job_SSN";
    public static final String JOB_COMP = "company";
    public static final String JOB_SAL = "salary";
    public static final String JOB_EXP = "experience";

    public static final String[] EMPLOYEE_HEADERS = {EMPLOYEE_SSN, EMPLOYEE_FIRST_NAME, EMPLOYEE_SECOND_NAME, EMPLOYEE_Y_O_B, EMPLOYEE_CITY};

    private DBHelper(Context context) {
        super(context,"db",null,DATABASE_VERSION);
   };

    private static DBHelper INSTANCE;
    public static synchronized DBHelper getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new DBHelper(context.getApplicationContext());
        }
        return INSTANCE;
    }


    @Override

       public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE " + EMPLOYEE_TABLE + " (" +
                EMPLOYEE_SSN + " INTEGER PRIMARY KEY NOT NULL, " +
                EMPLOYEE_FIRST_NAME + " TEXT, " +
                EMPLOYEE_SECOND_NAME + " TEXT, " +
                EMPLOYEE_Y_O_B + " TEXT, " +
                EMPLOYEE_CITY + " TEXT)");


        db.execSQL("CREATE TABLE " + JOB_TABLE + " (" +
                JOB_SSN + " INTEGER NOT NULL, " +
                JOB_COMP + " TEXT, " +
                JOB_SAL + " Text, " +
                JOB_EXP + " TEXT, " +
                "FOREIGN KEY(" + JOB_SSN + ") " +
                "REFERENCES " + EMPLOYEE_TABLE + "(" + EMPLOYEE_SSN + "))");

           }

//    finished creating tables


//    Adding people to the database

    public void insertEmployee(Employee employee){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues temp = new ContentValues();
        temp.put(EMPLOYEE_SSN, employee.getmSSN());
        temp.put(EMPLOYEE_FIRST_NAME, employee.getmFirstName());
        temp.put(EMPLOYEE_SECOND_NAME, employee.getmSecondName());
        temp.put(EMPLOYEE_Y_O_B, employee.getmYOB());
        temp.put(EMPLOYEE_CITY, employee.getmCity());
        db.insert(EMPLOYEE_TABLE,null,temp);
    }

    public void insertJobs(Job job){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues temp = new ContentValues();
        temp.put(JOB_SSN, job.getmSSN());
        temp.put(JOB_COMP, job.getmCompany());
        temp.put(JOB_SAL, job.getmSalary());
        temp.put(JOB_EXP, job.getmExperience());
        db.insertOrThrow(JOB_TABLE,null,temp);
    }

    private static final String DELETE_EMPLOYEE_TABLE = "DROP TABLE IF EXISTS " + EMPLOYEE_TABLE;
    private static final String DELETE_JOB_TABLE = "DROP TABLE IF EXISTS " + JOB_TABLE;

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DELETE_EMPLOYEE_TABLE);
        db.execSQL(DELETE_JOB_TABLE);
        onCreate(db);
    }

    public void removeCurrentTables() {
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL("DELETE FROM " + EMPLOYEE_TABLE);
        db.execSQL("DELETE FROM " + JOB_TABLE);
        db.close();
    }

    public Cursor findSimilarWorkers() {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT " + EMPLOYEE_FIRST_NAME + ", " + EMPLOYEE_SECOND_NAME
                + " FROM " + EMPLOYEE_TABLE + " JOIN " + JOB_TABLE
                + " ON " +  EMPLOYEE_TABLE + "." + EMPLOYEE_SSN + " = " + JOB_TABLE + "." + JOB_SSN
                + " WHERE " + JOB_COMP + " LIKE 'Macy%'";
        Cursor cursor = db.rawQuery(query,null);

        return cursor;
    }

    public Cursor bostonCompanies() {

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + EMPLOYEE_SSN + "," + EMPLOYEE_FIRST_NAME + ", " + EMPLOYEE_SECOND_NAME + " FROM "
                + EMPLOYEE_TABLE
                + " JOIN " + JOB_TABLE
                + " ON " + EMPLOYEE_TABLE + "." + EMPLOYEE_SSN + " = " + JOB_TABLE + "." + JOB_SSN
                + " WHERE " + EMPLOYEE_CITY + " = 'Boston'";
        Cursor  cursor = db.rawQuery(query,null);
        return cursor;
    }


//    SQL lite methods below

    public ArrayList<String> getAllEmployees() {

        // This works with my code in Main activity instead of the two methods above, why?????????????????????????????
        SQLiteDatabase db = getReadableDatabase();
        ArrayList temp = new ArrayList();
        Cursor cursor = db.query(EMPLOYEE_TABLE, EMPLOYEE_HEADERS, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                temp.add(cursor.getString(cursor.getColumnIndex(EMPLOYEE_FIRST_NAME + " ")));
                temp.add(cursor.getString(cursor.getColumnIndex(EMPLOYEE_SECOND_NAME)));
            }
        }
        return temp;
    }



//    public String giveNewID() {
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.query(EMPLOYEE_TABLE, EMPLOYEE_HEADERS, null, null, null, null, null, null);
//        String amount = (cursor.getCount() + 1) + "";
//        return amount;
//    }



//    public void addEmployee(String _id, String firtName, String lastName, String YOB, String city){
//        SQLiteDatabase db = getWritableDatabase();
//
//        String query =
//                " INSERT INTO " + EMPLOYEE_TABLE + " ( "
//                + EMPLOYEE_EMP_SSN + ", "
//                + EMPLOYEE_Prime_NAME + ", "
//                + EMPLOYEE_SECOND_NAME + ", "
//                + EMPLOYEE_Y_O_B + ", "
//                + EMPLOYEE_CITY + " ) "
//
//                + "VALUES( "
//                + _id + ", "
//                + firtName + ", "
//                + lastName + ", "
//                + YOB + ", "
//                + city + " ) ";
//        db.execSQL(query);
//    }

}
