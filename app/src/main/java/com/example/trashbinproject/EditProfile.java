package com.example.trashbinproject;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {
    DatabaseReference reference;
    FirebaseDatabase db;

    ImageButton register;
    String email, username, phone, password, retypepass;
    EditText Email, Username, Phone, Password, RetypePassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        FirebaseApp.initializeApp(this);

    }
}
