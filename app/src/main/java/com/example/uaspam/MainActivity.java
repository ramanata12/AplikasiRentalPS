package com.example.uaspam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uaspam.DB.DBHelper;

public class MainActivity extends AppCompatActivity {

    EditText TxUsername, TxPassword;
    TextView edRegister;
    Button BtnLogin;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TxUsername = (EditText) findViewById(R.id.lgNama);
        TxPassword = (EditText) findViewById(R.id.lgPassword);
        BtnLogin = (Button) findViewById(R.id.btSignin);
        edRegister = findViewById(R.id.edRegister);

        dbHelper = new DBHelper(this);


        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = TxUsername.getText().toString().trim();
                String password = TxPassword.getText().toString().trim();

                Boolean res = dbHelper.checkUser(username, password);
                if (res == true) {
                    Toast.makeText(MainActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Menu.class));

                } else {
                    Toast.makeText(MainActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }

            }
        });
        edRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Pendaftaran.class);
                startActivity(i);
            }
        });
    }
}
