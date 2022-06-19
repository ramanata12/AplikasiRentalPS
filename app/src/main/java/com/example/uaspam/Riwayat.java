package com.example.uaspam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.uaspam.adapter.UserAdapter;
import com.example.uaspam.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Riwayat extends AppCompatActivity {

    private Button tambah;
    private RecyclerView recyclerView;

    /*Firebase Firestore*/
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<User> list  = new ArrayList<>();
    private ProgressDialog progressDialog;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        tambah = findViewById(R.id.btnTambah);
        recyclerView = findViewById(R.id.daftarPesanan);

        // Penggunan Diaglong Loading
        progressDialog = new ProgressDialog(Riwayat.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");
        userAdapter = new UserAdapter(getApplicationContext(), list);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent(getApplicationContext(), Pesan.class);
                startActivity(data);
            }
        });


        userAdapter.setDialog(new UserAdapter.Dialog() {
            @Override
            public void onClick(int pos) {
                final  CharSequence[] dialogItem = {"Lihat Data", "Delete", "Edit Data"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(Riwayat.this);

                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            /*Lihat Data*/
                            case 0:
                                Intent lihat = new Intent(getApplicationContext(),LihatPesanan.class);
                                lihat.putExtra("nama", list.get(pos).getNama());
                                lihat.putExtra("telp", list.get(pos).getNotelp());
                                lihat.putExtra("alamat", list.get(pos).getAlamat());
                                lihat.putExtra("jam", list.get(pos).getJam());
                                lihat.putExtra("pesan", list.get(pos).getPesan());
                                lihat.putExtra("harga", list.get(pos).getHarga());
                                startActivity(lihat);
                                break;
                            /*Hapus Data*/
                            case 1:
                                deleteData(list.get(pos).getId());
                                break;
                            /*Edit Data*/
                            case 2:
                                Intent a = new Intent(getApplicationContext(), Pesan.class);
                                a.putExtra("nama", list.get(pos).getNama());
                                a.putExtra("telp", list.get(pos).getNotelp());
                                a.putExtra("alamat", list.get(pos).getAlamat());
                                a.putExtra("jam", list.get(pos).getJam());
                                a.putExtra("pesan", list.get(pos).getPesan());
                                a.putExtra("harga", list.get(pos).getHarga());
                                startActivity(a);

                        }
                    }
                });
                dialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(userAdapter);
    }

    private void deleteData(String id) {
        progressDialog.show();

        db.collection("Mesan").document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(Riwayat.this, "Data Gagal Di hapus", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Riwayat.this, "Data Berhasil Di Hapus", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        getData();
                    }
                });
    }

    @Override
    protected void onStart(){
        super.onStart();
        getData();
    }

    private void getData() {
        progressDialog.show();

        db.collection("Mesan")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                User pesanModel = new User(document.getString("nama"), document.getString("telp"),
                                        document.getString("alamat"), document.getString("jam"), document.getString("pesan"),
                                        document.getString("harga"));
                                pesanModel.setId(document.getId());
                                list.add(pesanModel);
                            }
                            userAdapter.notifyDataSetChanged();
                        }
                        else {
                            Toast.makeText(Riwayat.this, "Data Gagal di ambil", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
}