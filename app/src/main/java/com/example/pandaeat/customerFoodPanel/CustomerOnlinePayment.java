package com.example.pandaeat.customerFoodPanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pandaeat.CustomerFoofPanel_BottomNavigation;
import com.example.pandaeat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CustomerOnlinePayment extends AppCompatActivity {

    TextInputLayout cardname, cardnumber, expirydate, cvv;
    Button Addcard;
    String name, number, date, CVV;
    String RandomUID, ChefID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_online_payment);

        Addcard = (Button) findViewById(R.id.addcard);
        cardname = (TextInputLayout) findViewById(R.id.nameoncard);
        cardnumber = (TextInputLayout) findViewById(R.id.cardnumber);
        expirydate = (TextInputLayout) findViewById(R.id.expirydate);
        cvv = (TextInputLayout) findViewById(R.id.CVV);
        RandomUID = getIntent().getStringExtra("randomUID");

        Addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = cardname.getEditText().getText().toString().trim();
                number = cardnumber.getEditText().getText().toString().trim();
                date = expirydate.getEditText().getText().toString().trim();
                CVV = cvv.getEditText().getText().toString().trim();
                if (valid()) {
                  //  Intent intent = new Intent(CustomerOnlinePayment.this, CustomerPaymentOTP.class);
                   // intent.putExtra("RandomUID",RandomUID);
                    //startActivity(intent);
                    Toast.makeText(CustomerOnlinePayment.this, "payment in online", Toast.LENGTH_SHORT).show();
                }

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("CustomerPaymentOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            final CustomerPaymentOrders customerPaymentOrders = dataSnapshot1.getValue(CustomerPaymentOrders.class);
                            HashMap<String, String> hashMap = new HashMap<>();
                            String dishid = customerPaymentOrders.getDishId();
                            hashMap.put("ChefId", customerPaymentOrders.getChefId());
                            hashMap.put("DishId", customerPaymentOrders.getDishId());
                            hashMap.put("DishName", customerPaymentOrders.getDishName());
                            hashMap.put("DishPrice", customerPaymentOrders.getDishPrice());
                            hashMap.put("DishQuantity", customerPaymentOrders.getDishQuantity());
                            hashMap.put("RandomUID", RandomUID);
                            hashMap.put("TotalPrice", customerPaymentOrders.getTotalPrice());
                            hashMap.put("UserId", customerPaymentOrders.getUserId());
                            FirebaseDatabase.getInstance().getReference("CustomerFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes").child(dishid).setValue(hashMap);
                        }
                        DatabaseReference data = FirebaseDatabase.getInstance().getReference("CustomerPaymentOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("OtherInformation");
                        data.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                final CustomerPaymentOrders1 customerPaymentOrders1 = dataSnapshot.getValue(CustomerPaymentOrders1.class);
                                HashMap<String, String> hashMap1 = new HashMap<>();
                                hashMap1.put("Address", customerPaymentOrders1.getAddress());
                                hashMap1.put("GrandTotalPrice", customerPaymentOrders1.getGrandTotalPrice());
                                hashMap1.put("MobileNumber", customerPaymentOrders1.getMobileNumber());
                                hashMap1.put("Name", customerPaymentOrders1.getName());
                                hashMap1.put("Note", customerPaymentOrders1.getNote());
                                hashMap1.put("RandomUID", RandomUID);
                                hashMap1.put("Status", "Your order is waiting to be prepared by Chef...");
                                FirebaseDatabase.getInstance().getReference("CustomerFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("OtherInformation").setValue(hashMap1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        DatabaseReference Reference = FirebaseDatabase.getInstance().getReference("CustomerPaymentOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes");
                                        Reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                    final CustomerPaymentOrders customerPaymentOrderss = snapshot.getValue(CustomerPaymentOrders.class);
                                                    HashMap<String, String> hashMap2 = new HashMap<>();
                                                    String dishid = customerPaymentOrderss.getDishId();
                                                    ChefID = customerPaymentOrderss.getChefId();
                                                    hashMap2.put("ChefId", customerPaymentOrderss.getChefId());
                                                    hashMap2.put("DishId", customerPaymentOrderss.getDishId());
                                                    hashMap2.put("DishName", customerPaymentOrderss.getDishName());
                                                    hashMap2.put("DishPrice", customerPaymentOrderss.getDishPrice());
                                                    hashMap2.put("DishQuantity", customerPaymentOrderss.getDishQuantity());
                                                    hashMap2.put("RandomUID", RandomUID);
                                                    hashMap2.put("TotalPrice", customerPaymentOrderss.getTotalPrice());
                                                    hashMap2.put("UserId", customerPaymentOrderss.getUserId());
                                                    FirebaseDatabase.getInstance().getReference("ChefWaitingOrders").child(ChefID).child(RandomUID).child("Dishes").child(dishid).setValue(hashMap2);
                                                }
                                                DatabaseReference dataa = FirebaseDatabase.getInstance().getReference("CustomerPaymentOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("OtherInformation");
                                                dataa.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                        CustomerPaymentOrders1 customerPaymentOrders11 = dataSnapshot.getValue(CustomerPaymentOrders1.class);
                                                        HashMap<String, String> hashMap3 = new HashMap<>();
                                                        hashMap3.put("Address", customerPaymentOrders11.getAddress());
                                                        hashMap3.put("GrandTotalPrice", customerPaymentOrders11.getGrandTotalPrice());
                                                        hashMap3.put("MobileNumber", customerPaymentOrders11.getMobileNumber());
                                                        hashMap3.put("Name", customerPaymentOrders11.getName());
                                                        hashMap3.put("Note", customerPaymentOrders11.getNote());
                                                        hashMap3.put("RandomUID", RandomUID);
                                                        hashMap3.put("Status", "Your order is waiting to be prepared by Chef...");
                                                        FirebaseDatabase.getInstance().getReference("ChefWaitingOrders").child(ChefID).child(RandomUID).child("OtherInformation").setValue(hashMap3).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                FirebaseDatabase.getInstance().getReference("ChefPaymentOrders").child(ChefID).child(RandomUID).child("Dishes").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        FirebaseDatabase.getInstance().getReference("ChefPaymentOrders").child(ChefID).child(RandomUID).child("OtherInformation").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                            @Override
                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                FirebaseDatabase.getInstance().getReference("CustomerPaymentOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                        FirebaseDatabase.getInstance().getReference("CustomerPaymentOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("OtherInformation").removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                            @Override
                                                                                            public void onSuccess(Void aVoid) {
                                                                                                FirebaseDatabase.getInstance().getReference().child("Tokens").child(ChefID).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                                    @Override
                                                                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                                        String usertoken = dataSnapshot.getValue(String.class);
                                                                                                        //    sendNotifications(usertoken, "Order Confirmed", "Payment mode is confirmed by user, Now you can start the order", "Confirm");
                                                                                                    }

                                                                                                    @Override
                                                                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                                                    }
                                                                                                });

                                                                                            }
                                                                                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                            @Override
                                                                                            public void onSuccess(Void aVoid) {
                                                                                                AlertDialog.Builder builder = new AlertDialog.Builder(CustomerOnlinePayment.this);
                                                                                                builder.setMessage("Payment mode confirmed, Now you can track your order.");
                                                                                                builder.setCancelable(false);
                                                                                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                                                    @Override
                                                                                                    public void onClick(DialogInterface dialog, int which) {

                                                                                                        dialog.dismiss();
                                                                                                        Intent b = new Intent(CustomerOnlinePayment.this, CustomerFoofPanel_BottomNavigation.class);
                                                                                                        b.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                                                        startActivity(b);
                                                                                                        finish();

                                                                                                    }
                                                                                                });
                                                                                                AlertDialog alert = builder.create();
                                                                                                alert.show();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });

                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                });


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    private boolean valid() {


        cardname.setErrorEnabled(false);
        cardname.setError("");
        cardnumber.setErrorEnabled(false);
        cardnumber.setError("");
        expirydate.setErrorEnabled(false);
        expirydate.setError("");
        cvv.setErrorEnabled(false);
        cvv.setError("");


        boolean isValidname = false, isValidlnumber = false, isValidexpiry = false, isValidcvv = false, isvalid = false;
        if (TextUtils.isEmpty(name)) {
            cardname.setErrorEnabled(true);
            cardname.setError("Cardname is required");
        } else {
            isValidname = true;
        }
        if (TextUtils.isEmpty(number)) {
            cardnumber.setErrorEnabled(true);
            cardnumber.setError("Cardnumber is required");
        } else {
            if (number.length() < 16) {
                cardnumber.setErrorEnabled(true);
                cardnumber.setError("Invalid number");
            } else {
                isValidlnumber = true;
            }
        }
        if (TextUtils.isEmpty(date)) {
            expirydate.setErrorEnabled(true);
            expirydate.setError("Expiry date is required");
        } else {
            isValidexpiry = true;

        }
        if (TextUtils.isEmpty(CVV)) {
            cvv.setErrorEnabled(true);
            cvv.setError("CVV is required");
        } else {
            if (CVV.length() < 3) {
                cvv.setErrorEnabled(true);
                cvv.setError("Invalid CVV number");
            } else {
                isValidcvv = true;
            }
        }
        isvalid = (isValidname && isValidlnumber && isValidexpiry && isValidcvv) ? true : false;
        return isvalid;

    }
}