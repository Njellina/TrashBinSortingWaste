package com.example.trashbinproject;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class UserProfileFragment extends Fragment {

    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
    }
    public void onBackPressed() {
        // Disable back button
    }

    DatabaseReference reference;
    FirebaseDatabase db;

    EditText Email, Phone, Password;
    TextView Username;
    ImageButton back, logout, save;
    String usernameDB, emailDB, phoneDB;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_profile, container, false);

        back = view.findViewById(R.id.backbutt);
        logout = view.findViewById(R.id.logoutbutt);
        save  = view.findViewById(R.id.savebutt);
        Username = view.findViewById(R.id.username);
        Email = view.findViewById(R.id.email);
        Phone = view.findViewById(R.id.phone);

        db = FirebaseDatabase.getInstance("https://trashbin-project-default-rtdb.asia-southeast1.firebasedatabase.app/");
        reference = db.getReference("Users");

        Bundle name = getActivity().getIntent().getExtras();
        String username = (String) name.get("username");
        Query checkDatabase = reference.orderByChild("username").equalTo(username);

        checkDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    usernameDB = snapshot.child(username).child("username").getValue(String.class);
                    emailDB = snapshot.child(username).child("email").getValue(String.class);
                    phoneDB = snapshot.child(username).child("phone").getValue(String.class);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        Username.setText(usernameDB);
        Email.setText(emailDB);
        Phone.setText(phoneDB);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getContext(), Dashboard.class);
                startActivity(back);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(getContext(), MainActivity.class);
                startActivity(logout);
            }
        });
        return view;
    }
}

//if(loginn != null){
//        String name = (String) loginn.get("name");
//        Cursor cursor = db.ApplicantUsername(name);
//        if (cursor.moveToFirst()){
//        Name.setText(cursor.getString(0));
//        }
//        Intent login = new Intent(getContext(), ApplicantProfile.class);
//        login.putExtra("name", name);
//
//        Profile.setOnClickListener(new View.OnClickListener(){
//@Override
//public void onClick(View v){
//        startActivity(login);
//        }
//        });
//        }
