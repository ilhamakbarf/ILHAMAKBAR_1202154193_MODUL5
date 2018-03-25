package com.example.ilhamakbar.ilhamakbar_1202154193_modul5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ToDo extends AppCompatActivity {
    //deklarasi objek
    private EditText mtoDo, mDescription, mPriority;
    private Database mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        //set judul
        setTitle("Add To Do");

        //inisialisasi objek  ambil data dari xml
        mtoDo = (EditText) findViewById(R.id.edt_Todo);
        mDescription = (EditText) findViewById(R.id.edt_Desc);
        mPriority = (EditText) findViewById(R.id.edt_Priority);
        mdb = new Database(this);
    }

    @Override //method ketika kembali
    public void onBackPressed() {
        //intent ke main
        Intent intent = new Intent(ToDo.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    //method untuk menambahkan kegiatan
    public void addTodo(View view) {
        // jika menginput kegiatan
        if (mdb.inputdata(new AddData(mtoDo.getText().toString(), mDescription.getText().toString(), mPriority.getText().toString()))){
            //toast muncul
            Toast.makeText(this, "To Do List Ditambahkan !", Toast.LENGTH_SHORT).show();
            //intent menuju main
            startActivity(new Intent(ToDo.this, MainActivity.class));
            this.finish();
        }else { // jika tidak input
            Toast.makeText(this, "List tidak boleh kosong", Toast.LENGTH_SHORT).show();
            mtoDo.setText(null);
            mDescription.setText(null);
            mPriority.setText(null);
        }
    }

}
