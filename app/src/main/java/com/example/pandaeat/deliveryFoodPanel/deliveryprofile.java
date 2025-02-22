package com.example.pandaeat.deliveryFoodPanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pandaeat.Information;
import com.example.pandaeat.R;
import com.example.pandaeat.chefFoodPanel.Chef;
import com.example.pandaeat.chefFoodPanel.showprofile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class deliveryprofile extends AppCompatActivity {
    TextView firstname, lastname, address, passwordshow,emailshow;
    Spinner State, City, Suburban;
    TextView mobileno, Email;
    AppCompatButton Update;
    LinearLayout password, LogOut;
    DatabaseReference databaseReference, data;
    FirebaseDatabase firebaseDatabase;
    String statee, cityy, suburban, email, passwordd, confirmpass;

    TextView cityshow, areashow, stateshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveryprofile);

        cityshow=findViewById(R.id.cityshow);
        areashow=findViewById(R.id.areashow);
        stateshow=findViewById(R.id.stateshow);
        passwordshow=findViewById(R.id.passwordshow);
        emailshow=findViewById(R.id.emailshow);

        firstname = (TextView) findViewById(R.id.fnamee);
        lastname = (TextView) findViewById(R.id.lnamee);
        address = (TextView) findViewById(R.id.address);
        //  Email = (TextView) v.findViewById(R.id.emailID);
        //   State = (Spinner) v.findViewById(R.id.statee);
        //  City = (Spinner) v.findViewById(R.id.cityy);
        //    Suburban = (Spinner) v.findViewById(R.id.sub);
        mobileno = (TextView) findViewById(R.id.mobilenumber);
        Update = (AppCompatButton) findViewById(R.id.update);
        password = (LinearLayout) findViewById(R.id.passwordlayout);
        LogOut = (LinearLayout) findViewById(R.id.logout_layout);

 //       String userid = "h1qPyEfM9KPZXIHV9BxfjQcY3GE3";
       // databaseReference = FirebaseDatabase.getInstance().child("DeliveryPerson").child(userid);
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                final Delivery customer = dataSnapshot.getValue(Delivery.class);
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        firstname.setText("Md. Sakibur");
        lastname.setText("Rahman");
        address.setText("KUET");
        mobileno.setText("01794111768");
        //     Email.setText(customer.getEmailId());
        stateshow.setText("Khulna");
        cityshow.setText("Khulna");
        areashow.setText("KUET");
        passwordshow.setText("12345678");
        emailshow.setText("sakiburrahman11.msr@gmail.com");

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(deliveryprofile.this, Information.class));
            }
        });
    }
}