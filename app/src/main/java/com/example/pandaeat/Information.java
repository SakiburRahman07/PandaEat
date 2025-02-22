package com.example.pandaeat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Information extends AppCompatActivity {
        AppCompatButton appCompatButton,famousrestaurent,teacher, aboutus, diffrec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        appCompatButton=findViewById(R.id.famousfood);
        teacher=findViewById(R.id.teacherred);
        aboutus=findViewById(R.id.aboutusred);
        diffrec=findViewById(R.id.diffrecepired);

//        teacher.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Information.this, com.example.pandaeat.teacher.class));
//            }
//        });
//        aboutus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Information.this, com.example.pandaeat.aboutus.class));
//            }
//        });
//
//        diffrec.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Information.this, RecepiMainActivity.class));
//            }
//        });
//
//        famousrestaurent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Information.this, FamousRestaurent.class));
//            }
//        });
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Information.this, Famousfooddetails.class));
            }
        });
    }
}