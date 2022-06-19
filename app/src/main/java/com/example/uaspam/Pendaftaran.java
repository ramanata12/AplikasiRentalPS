package com.example.uaspam;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uaspam.DB.DBHelper;

public class Pendaftaran extends AppCompatActivity {

    EditText Username,Email, Password, ConPassword;
    Button BtnRegister;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran);

        dbHelper = new DBHelper(this);

        Username = (EditText)findViewById(R.id.rgNama);
        Email = (EditText)findViewById(R.id.rgEmail);
        Password = (EditText)findViewById(R.id.rgPass);
        ConPassword = (EditText)findViewById(R.id.rgRepass);
        BtnRegister = (Button)findViewById(R.id.btnRegis);


        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Username.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String conPassword = ConPassword.getText().toString().trim();

                ContentValues values = new ContentValues();


                if (!password.equals(conPassword)){
                    Toast.makeText(Pendaftaran.this, "Password tidak sama", Toast.LENGTH_SHORT).show();
                }else if (password.equals("") || username.equals("")){
                    Toast.makeText(Pendaftaran.this, "Nama atau Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else {
                    values.put(DBHelper.row_username, username);
                    values.put(DBHelper.row_password, password);
                    dbHelper.insertData(values);

                    Toast.makeText(Pendaftaran.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                    finish();

                }

            }
        });


    }
}