package com.example.trashbinproject;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class UserHomeFragment extends Fragment {

    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_home, container, false);


        return view;
    }
}
