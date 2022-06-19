package com.example.uaspam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LihatPS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_ps);

    }
    public void PlayStation2(View v) {
        Intent i = new Intent(this, Pesan.class);
        startActivity(i);
    }

    public void PlayStation3(View v) {
        Intent i = new Intent(this, Pesan.class);
        startActivity(i);
    }

    public void PlayStation4(View v) {
        Intent i = new Intent(this, Pesan.class);
        startActivity(i);
    }

    public void PlayStation5(View v) {
        Intent i = new Intent(this, Pesan.class);
        startActivity(i);
    }

}
