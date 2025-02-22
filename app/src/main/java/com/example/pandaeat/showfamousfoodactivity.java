package com.example.pandaeat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class showfamousfoodactivity extends AppCompatActivity {
    TextView recipiname,upokoron, pronali;
    AppCompatButton appCompatButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showfamousfoodactivity);
        recipiname=findViewById(R.id.recipename);
        upokoron=findViewById(R.id.upokoron);
        pronali=findViewById(R.id.pronali);
        appCompatButton=findViewById(R.id.jsonactivity);

        Intent intent = getIntent();
        String ageshow = intent.getStringExtra("age").toString();
        String cityshow = intent.getStringExtra("city").toString();
        String nameshow=intent.getStringExtra("name").toString();

        recipiname.setText(nameshow);
        upokoron.setText(cityshow);
        pronali.setText(ageshow);

        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(showfamousfoodactivity.this, Information.class));
            }
        });
    }
}