package com.example.trashbinproject;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    DatabaseReference reference;
    FirebaseDatabase db;
    ImageButton register, login;
    String username, password;
    EditText Username, Password;

    public void onBackPressed() {
        // Disable back button
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (ImageButton) findViewById(R.id.regbutt);
        login = (ImageButton) findViewById(R.id.loginbutt);
        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(getApplicationContext(), Register.class);
                startActivity(reg);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = Username.getText().toString();
                password = Password.getText().toString();

                if (username.isEmpty()) {
                    Username.setError("Username is required!");
                    Username.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    Password.setError("Password is required!");
                    Password.requestFocus();
                    return;
                }

                DatabaseReference reference = FirebaseDatabase.getInstance("https://trashbin-project-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users");
                Query checkDatabase = reference.orderByChild("username").equalTo(username);

                checkDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            String passDB = snapshot.child(username).child("password").getValue(String.class);

                            if (!Objects.equals(passDB, password)){
                                Password.setError("Password is invalid!");
                                Password.requestFocus();

                            }
                            else{
                                Intent login = new Intent(getApplicationContext(), Dashboard.class);
                                login.putExtra("username", username);
                                startActivity(login);
                            }
                        }
                        else{
                            Password.setError("User doesn't exist!");
                            Username.requestFocus();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }

                });
            }

        });
    }

}

