package com.example.trashbinproject;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Register extends AppCompatActivity {
    DatabaseReference reference;
    FirebaseDatabase db;

    ImageButton register;
    String email, username, phone, password, retypepass;
    EditText Email, Username, Phone, Password, RetypePassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        FirebaseApp.initializeApp(this);

        Email = findViewById(R.id.email);
        Username = findViewById(R.id.username);
        Phone = findViewById(R.id.phone);
        Password = findViewById(R.id.password);
        RetypePassword = findViewById(R.id.retypepass);

        register = (ImageButton) findViewById(R.id.regbutt);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = Email.getText().toString();
                username = Username.getText().toString();
                phone = Phone.getText().toString();
                password = Password.getText().toString();
                retypepass = RetypePassword.getText().toString();

                if (email.isEmpty()) {
                    Email.setError("Email is required!");
                    Email.requestFocus();
                    return;
                }
                if (username.isEmpty()) {
                    Username.setError("Username is required!");
                    Username.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Email.setError("Please provide valid email!");
                    Email.requestFocus();
                    return;
                }
                if (phone.isEmpty()) {
                    Phone.setError("Phone number is required!");
                    Phone.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    Password.setError("Password is required!");
                    Password.requestFocus();
                    return;
                }
                if (retypepass.isEmpty()) {
                    RetypePassword.setError("Please Re-type your password!");
                    RetypePassword.requestFocus();
                    return;
                }
                if(!retypepass.equals(password)){
                    RetypePassword.setError("Password doesn't match!");
                    RetypePassword.requestFocus();
                    return;
                }

                else {
                    db = FirebaseDatabase.getInstance("https://trashbin-project-default-rtdb.asia-southeast1.firebasedatabase.app/");
                    reference = db.getReference("Users");

                    DatabaseReference reference = FirebaseDatabase.getInstance("https://trashbin-project-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users");
                    Query checkDatabase = reference.orderByChild("username").equalTo(username);

                    checkDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                String verify = snapshot.child(username).child("username").getValue(String.class);

                                if (!Objects.equals(verify, username)){
                                    Users user = new Users(email, username, phone, password);
                                    reference.child(username).setValue(user);
                                    Toast.makeText(Register.this, "Registration completed!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Register.this, MainActivity.class);
                                    startActivity(intent);

                                }
                                else{
                                    Username.setError("Username already exists!");
                                    Username.requestFocus();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(Register.this, "Database Error!", Toast.LENGTH_SHORT).show();
                        }

                    });
                }
            }
        });
    }
}