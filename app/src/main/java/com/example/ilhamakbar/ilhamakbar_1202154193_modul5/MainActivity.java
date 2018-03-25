package com.example.ilhamakbar.ilhamakbar_1202154193_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //deklarasi variabel objek
    private Database mDtBase;
    private RecyclerView mRView;
    private Adapter mAdapter;
    private ArrayList<AddData> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set judul
        setTitle("Todo List");

        //inisialisasi objek ambil data dari xml
        //akses recyclerview
        mRView = findViewById(R.id.rec_view);
        list_data = new ArrayList<>(); //buat array baru
        mDtBase = new Database(this); //buat db baru
        mDtBase.readdata(list_data); //panggil method data_list

        //inisialisasi shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        mAdapter = new Adapter(this,list_data, color);
        //mengatur perubahan ukuran
        mRView.setHasFixedSize(true);
        mRView.setLayoutManager(new LinearLayoutManager(this));
        //inisiasi adapter untuk recycler view
        mRView.setAdapter(mAdapter);

        //menjalankan method hapus geser
        geserhapus();
    }

    //method untuk menghapus list dengan geser
    public void geserhapus(){
        //membuat touch helper baru untuk recycler view
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AddData current = mAdapter.getData(position);
                //jika item digeser ke kiri
                if(direction==ItemTouchHelper.LEFT){
                    //hapus item yang dipilih dengan primary key to donya
                    if(mDtBase.removedata(current.getTodo())){
                        mAdapter.deleteData(position);
                        //buat snackbar dan pemberitahuan bahwa item sudah terhapus dengan durasi 3 sekon
                        Snackbar.make(findViewById(R.id.coordinator), "List Telah Terhapus", 3000).show();
                    }
                }
            }
        };
        //tentuin itemtouchhelper untuk recycler view
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(mRView);
    }

    //ketika menu pada activity di buat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //method yang dijalankan ketika item di pilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //mendapatkan id dari item yang
        int id = item.getItemId();
        //jika klik settings
        if (id==R.id.action_settings){
            //intent ke menu Settings
            Intent intent = new Intent(MainActivity.this, Settings.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    //method yang di panggil pada tombol + di klik
    public void addlist(View view) {
        //intent ke class add to do
        Intent intent = new Intent(MainActivity.this, ToDo.class);
        startActivity(intent);
    }
}
