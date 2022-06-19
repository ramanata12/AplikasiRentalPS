package com.example.uaspam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class Pesan extends AppCompatActivity {

    /* Deklarasi variable Yang ada di dalam Layaout Pesan Makanan*/
    private EditText psnNama, psnTelp, psnAlamat, psnJam, psnPesan;
    private Button bnPesan;

    private String sPS;
    private int iHarga;

    /*Koversi*/
    private String harga;

    /*FirebaseFireStore*/
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private String id = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);

        /* Inisialisasi Variable */
        psnNama = findViewById(R.id.psNama);
        psnTelp = findViewById(R.id.psTelp);
        psnAlamat = findViewById(R.id.psAlamat);
        psnJam = findViewById(R.id.psJam);
        psnPesan = findViewById(R.id.psPesan);
        bnPesan = findViewById(R.id.btnPesan);

        /* Penggunaan Popup Loading */
        progressDialog = new ProgressDialog(Pesan.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Transaksi Berhasil...");

        bnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sPS = psnPesan.getText().toString();

                if (psnNama.getText().length() > 0 || psnTelp.getText().length() > 0 || psnAlamat.getText().length() > 0 ||
                        psnJam.getText().length() > 0 || psnPesan.getText().length() > 0) {

                    if (sPS.equals("PS2") || sPS.equals("ps2") || sPS.equals("playstation2") || sPS.equals("PlayStation2")) {
                        iHarga = 50000;
                    } else if (sPS.equals("PS3") || sPS.equals("ps3") || sPS.equals("playstation3") || sPS.equals("PlayStation3")) {
                        iHarga = 100000;
                    } else if (sPS.equals("PS4") || sPS.equals("ps4") || sPS.equals("playstation4") || sPS.equals("PlayStation4")) {
                        iHarga = 150000;
                    } else if (sPS.equals("PS5") || sPS.equals("ps5") || sPS.equals("playstation5") || sPS.equals("PlayStation5")) {
                        iHarga = 250000;
                    }

                    /*Convert int to String*/
                    harga = String.valueOf(iHarga);

                    /*Proses Create Data dan Di simpan ke dalam Firebase Firestore*/
                    saveData(psnNama.getText().toString(), psnTelp.getText().toString(), psnAlamat.getText().toString(), psnJam.getText().toString(), sPS, harga);
                } else {
                    Toast.makeText(Pesan.this, "Data Harus di isi Semua", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*Edit atau Update*/
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
            psnNama.setText(intent.getStringExtra("nama"));
            psnTelp.setText(intent.getStringExtra("telp"));
            psnAlamat.setText(intent.getStringExtra("alamat"));
            psnJam.setText(intent.getStringExtra("jam"));
            psnPesan.setText(intent.getStringExtra("pesan"));
        }
    }

    private void saveData(String nama, String telp, String alamat, String jam, String sPS, String harga) {
        Map<String, Object> mesan = new HashMap<>();

        mesan.put("nama", nama);
        mesan.put("telp", telp);
        mesan.put("alamat", alamat);
        mesan.put("jam", jam);
        mesan.put("pesan", sPS);
        mesan.put("harga", harga);

        progressDialog.show();

        if (id != null) {
            /**
             *  kode untuk edit data firestore dengan mengambil id
             */
            db.collection("Mesan").document(id)
                    .set(mesan)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Pesan.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(Pesan.this, "Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            db.collection("Mesan")
                    .add(mesan)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(Pesan.this, "Berhasil di simpan", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), Menu.class);
                            startActivity(i);
                            progressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Pesan.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        }
    }
}




