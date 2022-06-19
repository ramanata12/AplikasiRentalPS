package com.example.uaspam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }
    public void Riwayat(View v) {
        Intent i = new Intent(this, Riwayat.class);
        startActivity(i);
    }

    public void Pesan(View v) {
        Intent i = new Intent(this, Pesan.class);
        startActivity(i);
    }

    public void PlayStation(View v) {
        Intent i = new Intent(this, LihatPS.class);
        startActivity(i);
    }

    public void Logout(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}