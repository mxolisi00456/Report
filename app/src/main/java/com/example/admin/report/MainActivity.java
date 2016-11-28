package com.example.admin.report;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper myDb;
    EditText editName, editSurname, editMarks, editTextID;
    Button btnAddData;
    Button btnViewAll;
    Button btnUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DataBaseHelper(this);

        editName = (EditText) findViewById(R.id.edit_name);
        editSurname = (EditText) findViewById(R.id.edit_surname);
        editMarks = (EditText) findViewById(R.id.edit_marks);
        editTextID = (EditText) findViewById(R.id.edit_id);
        btnAddData = (Button) findViewById(R.id.button_add);
        btnViewAll = (Button) findViewById(R.id.button_view);
        btnUpdate = (Button) findViewById(R.id.button_update);
        btnDelete = (Button) findViewById(R.id.button_delete);
        AddData();
        viewAll();


        UpdateData();


        DeleteData();

    }
    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextID.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(MainActivity.this, "Data is Deleted", Toast.LENGTH_LONG).show();

                        else
                            Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }});}

    public void UpdateData() {
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(
                                editTextID.getText().toString(),
                                editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString()
                        );
                        if (isUpdate)
                            Toast.makeText(MainActivity.this, "Data is updated", Toast.LENGTH_LONG).show();

                        else
                            Toast.makeText(MainActivity.this, "Data not updated", Toast.LENGTH_LONG).show();
                    }
                }


        );
    }







                    public void AddData() {
                        btnAddData.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                                editSurname.getText().toString(),
                                                editMarks.getText().toString());

                                        if (isInserted)
                                            Toast.makeText(MainActivity.this, "Data is Inserted", Toast.LENGTH_LONG).show();

                                        else
                                            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();

                                    }
                                }
                        );
                    }


                    public void viewAll() {
                        btnViewAll.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Cursor res = myDb.getAllData();
                                        if (res.getCount() == 0) {
                                            //Show message
                                            showMessage("Error", "No data found");
                                            return;

                                        }
                                        StringBuffer buffer = new StringBuffer();
                                        while (res.moveToNext()) {
                                            buffer.append("Id :" + res.getString(0) + "\n");
                                            buffer.append("Name :" + res.getString(1) + "\n");
                                            buffer.append("Surname :" + res.getString(2) + "\n");
                                            buffer.append("Marks :" + res.getString(3) + "\n\n");
                                        }
                                        //Show all data
                                        showMessage("Data", buffer.toString());


                                    }

                                }
                        );
                    }

                    public void showMessage(String title, String Message) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setCancelable(true);
                        builder.setTitle(title);
                        builder.setMessage(Message);
                        builder.show();
                    }}