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

public class Loginphone extends AppCompatActivity {

    EditText num;
    AppCompatButton sendotp,signinemail;
    TextView signup;
 //   CountryCodePicker cpp;
    FirebaseAuth Fauth;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginphone);

        num = (EditText)findViewById(R.id.number);
        sendotp = (AppCompatButton) findViewById(R.id.otp);
        //cpp=(CountryCodePicker)findViewById(R.id.CountryCode);
        signinemail=(AppCompatButton) findViewById(R.id.btnEmail);
        signup = (TextView)findViewById(R.id.acsignup);

        Fauth = FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number=num.getText().toString().trim();
              //  String Phonenum = cpp.getSelectedCountryCodeWithPlus()+number;
                String Phonenum = "+88"+number;
                Intent b = new Intent(Loginphone.this,sendotp.class);

                b.putExtra("Phonenumber",Phonenum);
                startActivity(b);
                finish();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Loginphone.this,Registration.class));
                finish();
            }
        });
        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Loginphone.this,Login.class));
                finish();
            }
        });
    }
}