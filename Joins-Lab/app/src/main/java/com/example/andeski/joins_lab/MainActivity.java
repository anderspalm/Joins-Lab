package com.example.andeski.joins_lab;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mAddData;
    Button mSimilarComp;
    Button mBoston;
    Button mHighSalary;
    EditText mssn;
    EditText mFirstName;
    EditText mLastName;
    EditText mYOB;
    EditText mCity;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.selections);

//      Add data
        mAddData = (Button) findViewById(R.id.add_data);
        mAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper helper = DBHelper.getInstance(MainActivity.this);
                helper.removeCurrentTables();

//                helper.insertEmployee(new Employee("1","123-04-5678","John","Smith","1973","NY"));
//                helper.insertEmployee(new Employee("2","123-04-5679","David","McWill","1982","Seattle"));
//                helper.insertEmployee(new Employee("3","123-04-5680","Katerina","Wise","1973","Boston"));
//                helper.insertEmployee(new Employee("4","123-04-5681","Donald","Lee","1992","London"));
//                helper.insertEmployee(new Employee("5","123-04-5682","Gary","Henwood","1987","Las Vegas"));
//                helper.insertEmployee(new Employee("6","123-04-5683","Anthony","Bright","1963","Seattle"));
//                helper.insertEmployee(new Employee("7","123-04-5684","William","Newey","1995","Boston"));
//                helper.insertEmployee(new Employee("8","123-04-5685","Melony","Smith","1970","Chicago"));

                helper.insertEmployee(new Employee(123-04-5678, "John", "Smith", "1973", "NY"));
                helper.insertEmployee(new Employee(123-04-5679, "David", "McWill", "1982", "Seattle"));
                helper.insertEmployee(new Employee(123-04-5680, "Katerina", "Wise", "1973", "Boston"));
                helper.insertEmployee(new Employee(123-04-5681, "Donald", "Lee", "1992", "London"));
                helper.insertEmployee(new Employee(123-04-5682, "Gary", "Henwood", "1987", "Las Vegas"));
                helper.insertEmployee(new Employee(123-04-5683, "Anthony", "Bright", "1963", "Seattle"));
                helper.insertEmployee(new Employee(123-04-5684, "William", "Newey", "1995", "Boston"));
                helper.insertEmployee(new Employee(123-04-5685, "Melony", "Smith", "1970", "Chicago"));

                helper.insertJobs(new Job( 123-04-5678,"Fuzz", "60", "1"));
                helper.insertJobs(new Job( 123-04-5679,"GA", "70", "2"));
                helper.insertJobs(new Job( 123-04-5680,"Little Place", "120", "5"));
                helper.insertJobs(new Job( 123-04-5681,"Macy's", "78", "3"));
                helper.insertJobs(new Job( 123-04-5682,"New Life", "65", "1"));
                helper.insertJobs(new Job( 123-04-5683,"Believe", "158", "6"));
                helper.insertJobs(new Job( 123-04-5684,"Macy's", "200", "8"));
                helper.insertJobs(new Job( 123-04-5685,"Stop", "299", "12"));

                Toast.makeText(MainActivity.this, "You added to the database successfully", Toast.LENGTH_SHORT).show();
            }
        });

//         Add Floating Action Button functionality

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.add_employee_edit_views, null);

                mssn = (EditText) dialogView.findViewById(R.id.ssn);
                mFirstName = (EditText) dialogView.findViewById(R.id.firstName);
                mLastName = (EditText) dialogView.findViewById(R.id.lastName);
                mYOB = (EditText) dialogView.findViewById(R.id.yearOfBirth);
                mCity = (EditText) dialogView.findViewById(R.id.city);

                dialog.setView(dialogView);
                dialog.setTitle(" New Employee ");

                dialog.setPositiveButton("Submit Data", null);
                dialog.setNegativeButton("Cancel", null);

                final AlertDialog overriding_dialog = dialog.create();
                overriding_dialog.show();

                overriding_dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (
                                (mssn.getText().toString().trim().length() == 0) ||
                                        (mFirstName.getText().toString().trim().length() == 0) ||
                                        (mLastName.getText().toString().trim().length() == 0) ||
                                        (mYOB.getText().toString().trim().length() == 0) ||
                                        (mCity.getText().toString().trim().length() == 0)
                                ) {
                            Toast.makeText(MainActivity.this, "You left a field blank", Toast.LENGTH_SHORT).show();
                        } else {
                            Employee temp = new Employee();
                            Integer i = Integer.parseInt(mssn.getText().toString());
                            temp.setmSSN(i);
                            temp.setmFirstName(mFirstName.getText().toString().trim());
                            temp.setmSecondName(mLastName.getText().toString().trim());
                            temp.setmYOB(mYOB.getText().toString().trim());
                            temp.setmCity(mCity.getText().toString().trim());

                            DBHelper new_helper = DBHelper.getInstance(MainActivity.this);
                            new_helper.insertEmployee(temp);
                            Toast.makeText(MainActivity.this, "You Successfully Added " + temp.getmFirstName(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });

//      Finding similar workers

        mSimilarComp = (Button) findViewById(R.id.employees_w_a_t_s_c);
        mSimilarComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Toast.makeText(MainActivity.this, "Success... open", Toast.LENGTH_SHORT).show();
                Cursor cursor = DBHelper.getInstance(MainActivity.this).findSimilarWorkers();
                displayNames(cursor);
            }
        });

        mBoston = (Button) findViewById(R.id.companies_in_Boston);
    }




    // end of main activity method


            public void displayNames(Cursor cursor){
                CursorAdapter cursorAdapter = new CursorAdapter(MainActivity.this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {

                    @Override
                    public View newView(Context context, Cursor cursor, ViewGroup parent) {
                        Toast.makeText(MainActivity.this, "Success... inside adapter", Toast.LENGTH_SHORT).show();
                        return LayoutInflater.from(MainActivity.this).inflate(R.layout.list_view_items, parent, false);
                    }

                    @Override
                    public void bindView(View view, Context context, Cursor cursor) {
                        TextView fullName = (TextView) view.findViewById(R.id.list_item);

                        String first = cursor.getString(cursor.getColumnIndex(DBHelper.EMPLOYEE_FIRST_NAME));
                        String second = cursor.getString(cursor.getColumnIndex(DBHelper.EMPLOYEE_SECOND_NAME));

                        fullName.setText(first + " " + second);
                        Toast.makeText(MainActivity.this, "Success... set the view", Toast.LENGTH_SHORT).show();

                    }
                };

                mListView.setAdapter(cursorAdapter);
            }

}

