package com.example.pandaeat;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
//import com.hbb20.CountryCodePicker;

public class Delivery_Loginphone extends AppCompatActivity {

    EditText num;
    AppCompatButton sendotp,signinemail;
    TextView signup;
   // CountryCodePicker cpp;
    FirebaseAuth Fauth;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_loginphone);

        num = (EditText)findViewById(R.id.Dphonenumber);
        sendotp = (AppCompatButton) findViewById(R.id.Sendotp);
       // cpp=(CountryCodePicker)findViewById(R.id.countrycode);
        signinemail=(AppCompatButton) findViewById(R.id.DbtnEmail);
        signup = (TextView)findViewById(R.id.Signupif);

        Fauth = FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number=num.getText().toString().trim();
               // String Phonenum = cpp.getSelectedCountryCodeWithPlus()+number;
                String Phonenum = "+88"+number;
                Intent b = new Intent(Delivery_Loginphone.this,Delivery_sendotp.class);

                b.putExtra("Phonenum",Phonenum);
                startActivity(b);
                finish();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Delivery_Loginphone.this,Delivery_Registration.class));
                finish();
            }
        });
        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Delivery_Loginphone.this,Delivery_Login.class));
                finish();
            }
        });


    }
}