package com.example.trashbinproject;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class UserListFragment extends Fragment {

    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
    }

    ImageButton back;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_list, container, false);

        back = view.findViewById(R.id.backbutt);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getContext(), Dashboard.class);
                startActivity(back);
            }
        });

        return view;
    }
}
