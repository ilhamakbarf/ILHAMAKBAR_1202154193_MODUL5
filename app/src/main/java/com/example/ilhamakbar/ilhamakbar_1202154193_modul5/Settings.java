package com.example.ilhamakbar.ilhamakbar_1202154193_modul5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    private TextView mWarna; //deklarasi objek
    int mColorid;
    AlertDialog.Builder mAlert; //alert
    SharedPreferences.Editor mSharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings"); //judul

        //membuat alert baru
        mAlert = new AlertDialog.Builder(this);

        //inisialisasi shared preferensi
        SharedPreferences shareP = getApplicationContext().getSharedPreferences("Preferences", 0);
        mSharedpref = shareP.edit();
        mColorid = shareP.getInt("Colourground", R.color.white);

        mWarna = findViewById(R.id.shapecolor); //inisialisasi objek warna
        mWarna.setText(getShapeColor(mColorid)); // set warna
    }

    //action tombol back
    @Override
    public void onBackPressed() {
        //intent dari setting ke main activity
        Intent intent = new Intent(Settings.this, MainActivity.class);
        startActivity(intent); // start intent
        finish();
    }

    //method yang memberikan perintah ke setiap option menu // memilih pilihan
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            this.onBackPressed(); //menjalankan method onBackPresed
        }
        return true;
    }

    //get String untuk merubah warna
    public String getShapeColor(int i){
        if (i==R.color.red){
            return "Red";
        }else if (i==R.color.green){
            return "Green";
        }else if (i==R.color.blue){
            return "Blue";
        }else{
            return "Default";
        }
    }

    //mendapatkan id dari warna yang akan digunakan
    public int getColorid(int i){
        if (i==R.color.red){
            return R.id.red;
        }else if (i==R.color.green){
            return R.id.green;
        }else if (i==R.color.blue){
            return R.id.blue;
        }else{
            return R.id.white;
        }
    }

    public void pilihWarna(View view) { //method untuk memilih warna
        mAlert.setTitle("Shape Color");//set judul
        //buat view baru
        View Mview = getLayoutInflater().inflate(R.layout.setting_warna, null);
        mAlert.setView(Mview);

        //akses radio grup yang diambil dari xml
        final RadioGroup radG = Mview.findViewById(R.id.radio_color);
        radG.check(getColorid(mColorid));

        //action button OK pada dialog
        mAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //get radio button
                int a = radG.getCheckedRadioButtonId();
                // kondisi jika memilih radio button sesuai dengan warna
                switch (a){
                    case R.id.red:
                        mColorid = R.color.red;
                        break;
                    case R.id.green:
                        mColorid = R.color.green;
                        break;
                    case R.id.blue:
                        mColorid = R.color.blue;
                        break;
                    case R.id.white:
                        mColorid = R.color.white;
                        break;
                }
                //set shape color menjadi color id yang dipilih
                mWarna.setText(getShapeColor(mColorid));
                //menyimpan shared preference
                mSharedpref.putInt("Colourground", mColorid);
                mSharedpref.commit();
            }
        });

        //action button cancel pada dialog
        mAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mAlert.create().show(); //membuat dan nampilkan alert
    }


}
