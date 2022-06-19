package com.example.uaspam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class LihatPesanan extends AppCompatActivity {

    private TextView  nama, telp, alamat, jam, pesan, harga;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_pesanan);

        nama = findViewById(R.id.lpNama);
        telp = findViewById(R.id.lpNotelp);
        alamat = findViewById(R.id.lpAlamat);
        jam = findViewById(R.id.lpJam);
        pesan = findViewById(R.id.lpPesan);
        harga = findViewById(R.id.lpHarga);

        progressDialog = new ProgressDialog(LihatPesanan.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil Data...");

        Intent intent = getIntent();

        if(intent != null){
            nama.setText(intent.getStringExtra("nama"));
            telp.setText(intent.getStringExtra("telp"));
            alamat.setText(intent.getStringExtra("alamat"));
            jam.setText(intent.getStringExtra("jam"));
            pesan.setText(intent.getStringExtra("pesan"));
            harga.setText(intent.getStringExtra("harga"));
        }
    }
}