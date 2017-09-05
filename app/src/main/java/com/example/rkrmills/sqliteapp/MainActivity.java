package com.example.rkrmills.sqliteapp;

import android.database.Cursor;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Databasehelper myDb;
    EditText EditUsername,EditPwd,EditId;
    Button btnAddData;
    Button btnView;
    Button btnUpdate;
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new Databasehelper(this);

        EditUsername=(EditText)findViewById(R.id.editText1_username);
        EditPwd=(EditText)findViewById(R.id.editText2_pwd);
        EditId=(EditText)findViewById(R.id.editText_ID);
        btnAddData=(Button)findViewById(R.id.button_insert);
        btnView=(Button)findViewById(R.id.btn_view);
        btnUpdate=(Button)findViewById(R.id.btn_update);
        btnDelete=(Button)findViewById(R.id.btn_delete);
        addData();
        viewAll();
        UpdateData();
        DeleteData();
    }

    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        Integer deleteRows=myDb.deleteData(EditId.getText().toString());
                        if(deleteRows>0)
                            Toast.makeText(MainActivity.this,"Data deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"data not deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public  void UpdateData(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                       boolean isUpdated=myDb.updateData(EditId.getText().toString(),EditUsername.getText().toString(),EditPwd.getText().toString());
                        if(isUpdated==true)
                            Toast.makeText(MainActivity.this,"Data updated",Toast.LENGTH_LONG).show();
                            else
                            Toast.makeText(MainActivity.this,"data not updated",Toast.LENGTH_LONG).show();


                    }
                }
        );
    }

    public void addData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                    boolean isInserted= myDb.insertData(EditUsername.getText().toString(),EditPwd.getText().toString());
                    if(isInserted==true)
                        Toast.makeText(MainActivity.this,"Data inserted",Toast.LENGTH_LONG).show();
                        else
                        Toast.makeText(MainActivity.this,"data not inserted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void viewAll(){
            btnView.setOnClickListener(
                    new View.OnClickListener(){

                        @Override
                        public void onClick(View view) {
                          Cursor res= myDb.getAllData();//it returns object of cursor therefore we r storing into cursor
                           if(res.getCount()==0){
                               //show message
                               showMessage("Error","Nothing found");
                               return;
                           }
                            StringBuffer buffer=new StringBuffer();
                            while(res.moveToNext()){
                                buffer.append("ID :"+res.getString(0)+"\n");
                                buffer.append("USERNAME :"+res.getString(1)+"\n");
                                buffer.append("PASSWORD :"+res.getString(2)+"\n");

                            }
                            //SHOW ALL DATA
                            showMessage("Data",buffer.toString());
                        }
                    }
            );

    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();


    }
}
