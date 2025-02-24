package com.example.pandaeat.deliveryFoodPanel;

import static android.Manifest.permission_group.PHONE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pandaeat.DeliveryFoodPanel_BottomNavigation;
import com.example.pandaeat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Delivery_ShippingOrder extends AppCompatActivity {
    TextView Address, ChefName, grandtotal, MobileNumber, Custname;
    Button Call, Shipped;
  //  private APIService apiService;
    LinearLayout l1, l2;
    String randomuid;
    String userid, Chefid;
    String deliveryId = "oCpc4SwLVFbKO0fPdtp4R6bmDmI3";
    static int PERMISSION_CODE=100;
    String mob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_shipping_order);
        Address = (TextView) findViewById(R.id.ad3);
        ChefName = (TextView) findViewById(R.id.chefname2);
        grandtotal = (TextView) findViewById(R.id.Shiptotal1);
        MobileNumber = (TextView) findViewById(R.id.ShipNumber1);
        Custname = (TextView) findViewById(R.id.ShipName1);
        l1 = (LinearLayout) findViewById(R.id.linear3);
        l2 = (LinearLayout) findViewById(R.id.linearl1);
        Call = (Button) findViewById(R.id.call2);
        Shipped = (Button) findViewById(R.id.shipped2);
       // apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);
        Shipped();


        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(Delivery_ShippingOrder.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(Delivery_ShippingOrder.this, new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CODE);
                }
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("DeliveryShipFinalOrders").child(deliveryId).child(randomuid).child("OtherInformation");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DeliveryShipFinalOrders1 deliveryShipFinalOrders1 = snapshot.getValue(DeliveryShipFinalOrders1.class);
                         mob = deliveryShipFinalOrders1.getMobileNumber();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+mob));
                startActivity(i);

            }
        });
    }
    private void Shipped() {

        randomuid = getIntent().getStringExtra("RandomUID");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("DeliveryShipFinalOrders").child(deliveryId).child(randomuid).child("OtherInformation");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DeliveryShipFinalOrders1 deliveryShipFinalOrders1 = dataSnapshot.getValue(DeliveryShipFinalOrders1.class);
                grandtotal.setText("৳ " + deliveryShipFinalOrders1.getGrandTotalPrice());
                Address.setText(deliveryShipFinalOrders1.getAddress());
                Custname.setText(deliveryShipFinalOrders1.getName());
                MobileNumber.setText("+88" + deliveryShipFinalOrders1.getMobileNumber());
                ChefName.setText("Chef " + deliveryShipFinalOrders1.getChefName());
                userid = deliveryShipFinalOrders1.getUserId();
                Chefid = deliveryShipFinalOrders1.getChefId();
                Shipped.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseDatabase.getInstance().getReference("CustomerFinalOrders").child(userid).child(randomuid).child("OtherInformation").child("Status").setValue("Your Order is delivered").addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                FirebaseDatabase.getInstance().getReference().child("Tokens").child(userid).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String usertoken = dataSnapshot.getValue(String.class);
                                       // sendNotifications(usertoken, "Home Chef", "Thank you for Ordering", "ThankYou");
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                FirebaseDatabase.getInstance().getReference().child("Tokens").child(Chefid).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String usertoken = dataSnapshot.getValue(String.class);
                                        //sendNotifications(usertoken, "Order Placed", "Your food has been delivered to Customer's Doorstep", "Delivered");
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Delivery_ShippingOrder.this);
                                builder.setMessage("Order is delivered, Now you can check for new Orders");
                                builder.setCancelable(false);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        dialog.dismiss();
                                        Intent intent = new Intent(Delivery_ShippingOrder.this, DeliveryFoodPanel_BottomNavigation.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();


                                    }
                                });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                FirebaseDatabase.getInstance().getReference("CustomerFinalOrders").child(userid).child(randomuid).child("Dishes").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        FirebaseDatabase.getInstance().getReference("CustomerFinalOrders").child(userid).child(randomuid).child("OtherInformation").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                FirebaseDatabase.getInstance().getReference("DeliveryShipFinalOrders").child(deliveryId).child(randomuid).child("Dishes").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        FirebaseDatabase.getInstance().getReference("DeliveryShipFinalOrders").child(deliveryId).child(randomuid).child("OtherInformation").removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                FirebaseDatabase.getInstance().getReference("AlreadyOrdered").child(userid).child("isOrdered").setValue("false");
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
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

   /* private void sendNotifications(String usertoken, String title, String message, String order) {

        Data data = new Data(title, message, order);
        NotificationSender sender = new NotificationSender(data, usertoken);
        apiService.sendNotification(sender).enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(retrofit2.Call<MyResponse> call, Response<MyResponse> response) {
                if (response.code() == 200) {
                    if (response.body().success != 1) {
                        Toast.makeText(Delivery_ShippingOrder.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<MyResponse> call, Throwable t) {

            }
        });
    }*/
}