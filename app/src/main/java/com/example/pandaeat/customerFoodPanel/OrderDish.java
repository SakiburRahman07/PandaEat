package com.example.pandaeat.customerFoodPanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pandaeat.CustomerFoofPanel_BottomNavigation;
import com.example.pandaeat.R;
import com.example.pandaeat.UpdateDishModel;
import com.example.pandaeat.chefFoodPanel.Chef;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class OrderDish extends AppCompatActivity {

    String RandomId, ChefID;
    ImageView imageView;
    EditText additem;
    TextView Foodname, ChefName, ChefLoaction, FoodQuantity, FoodPrice, FoodDescription;
    DatabaseReference databaseReference, dataaa, chefdata, reference, data, dataref;
    String State, City, Area, dishname;
    int dishprice;
    String custID;
    FirebaseDatabase firebaseDatabase;

    AppCompatButton additembutton;

    RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_dish);

        Foodname = (TextView) findViewById(R.id.food_name);
        ChefName = (TextView) findViewById(R.id.chef_name);
        ChefLoaction = (TextView) findViewById(R.id.chef_location);
        FoodQuantity = (TextView) findViewById(R.id.food_quantity);
        FoodPrice = (TextView) findViewById(R.id.food_price);
        FoodDescription = (TextView) findViewById(R.id.food_description);
        imageView = (ImageView) findViewById(R.id.image);
        additem = (EditText) findViewById(R.id.number_btn);
        ratingBar=findViewById(R.id.ratingBar);

        additembutton=findViewById(R.id.placeorderbutton);

        final String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dataaa = FirebaseDatabase.getInstance().getReference("Customer").child(userid);
        dataaa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Customer cust = dataSnapshot.getValue(Customer.class);
                State = cust.getState();
                City = cust.getCity();
                Area = cust.getArea();

                RandomId = getIntent().getStringExtra("FoodMenu");
                ChefID = getIntent().getStringExtra("ChefId");

                String tmp = "FoodDetails/"+State+"/"+City+"/"+Area+"/"+ChefID+"/"+RandomId;
                System.out.println(tmp);

              //  databaseReference = FirebaseDatabase.getInstance().getReference("FoodDetails").child(State).child(City).child(Area).child(ChefID).child(RandomId);
                databaseReference = FirebaseDatabase.getInstance().getReference("FoodDetails").child(State).child(City).child(Area).child(ChefID).child(RandomId);;
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        UpdateDishModel updateDishModel = dataSnapshot.getValue(UpdateDishModel.class);

                        if(updateDishModel == null){
                            Toast.makeText(OrderDish.this, "No data found", Toast.LENGTH_SHORT).show();
                            return;
                        }
//rating

                        ratingBar.setRating(Float.valueOf(updateDishModel.getRating()));

                        Foodname.setText(updateDishModel.getDishes());
                        String qua = "<b>" + "Quantity: " + "</b>" + updateDishModel.getQuantity();
                        FoodQuantity.setText(Html.fromHtml(qua));
                        String ss = "<b>" + "Description: " + "</b>" + updateDishModel.getDescription();
                        FoodDescription.setText(Html.fromHtml(ss));
                        String pri = "<b>" + "Price: ৳ " + "</b>" + updateDishModel.getPrice();
                        FoodPrice.setText(Html.fromHtml(pri));
                        Glide.with(OrderDish.this).load(updateDishModel.getImageURL()).into(imageView);

                        chefdata = FirebaseDatabase.getInstance().getReference("Chef").child(ChefID);
                        chefdata.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Chef chef = dataSnapshot.getValue(Chef.class);

                                String name = "<b>" + "Chef Name: " + "</b>" + chef.getFname() + " " + chef.getLname();
                                ChefName.setText(Html.fromHtml(name));
                                String loc = "<b>" + "Location: " + "</b>" + chef.getArea();
                                ChefLoaction.setText(Html.fromHtml(loc));
                                custID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                databaseReference = FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(custID).child(RandomId);
                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Cart cart = dataSnapshot.getValue(Cart.class);
                                        if (dataSnapshot.exists()) {
                                            additem.setText(cart.getDishQuantity());
                                        }
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

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        additembutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dataref = FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                dataref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Cart cart1=null;
                        if (dataSnapshot.exists()) {
                            int totalcount=0;
                            for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                totalcount++;
                            }
                            int i=0;
                            for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                i++;
                                if(i==totalcount){
                                    cart1= snapshot.getValue(Cart.class);
                                }
                            }

                            if (ChefID.equals(cart1.getChefId())) {
                                data = FirebaseDatabase.getInstance().getReference("FoodDetails").child(State).child(City).child(Area).child(ChefID).child(RandomId);
                                data.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        UpdateDishModel update = dataSnapshot.getValue(UpdateDishModel.class);
                                        dishname = update.getDishes();
                                        dishprice = Integer.parseInt(update.getPrice());
                                      //  String ratingstring = String.valueOf(ratingBar.getRating());

                                        String num = additem.getText().toString();
                                        int totalprice =Integer.parseInt( num) * dishprice;
                                        if (Integer.parseInt(num) != 0) {
                                            HashMap<String, String> hashMap = new HashMap<>();
                                            hashMap.put("DishName", dishname);
                                            hashMap.put("DishID", RandomId);
                                            hashMap.put("DishQuantity", String.valueOf(num));
                                            hashMap.put("Price", String.valueOf(dishprice));
                                            hashMap.put("Totalprice", String.valueOf(totalprice));
                                            hashMap.put("ChefId", ChefID);
                                         //   hashMap.put("Rating",ratingstring );
                                            custID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                            reference = FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(custID).child(RandomId);
                                            reference.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {

                                                    Toast.makeText(OrderDish.this, "Added to cart", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                        } else {

                                            firebaseDatabase.getInstance().getReference("Cart").child(custID).child(RandomId).removeValue();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(OrderDish.this);
                                builder.setMessage("You can't add food items of multiple chef at a time. Try to add items of same chef");
                                builder.setCancelable(false);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        dialog.dismiss();
                                        Intent intent = new Intent(OrderDish.this, CustomerFoofPanel_BottomNavigation.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }
                        } else {
                            data = FirebaseDatabase.getInstance().getReference("FoodDetails").child(State).child(City).child(Area).child(ChefID).child(RandomId);
                            data.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    UpdateDishModel update = dataSnapshot.getValue(UpdateDishModel.class);
                                    dishname = update.getDishes();
                                    dishprice = Integer.parseInt(update.getPrice());
                                    Float f = ratingBar.getRating();
                                    Float curr = Float.valueOf(update.getRating());
                                    f = (f+curr)/2;
                                   String ratingstring = String.valueOf(f);

                                    data.child("Rating").setValue(ratingstring);

                                    String num =additem.getText().toString();
                                    int totalprice = Integer.parseInt(num) * dishprice;
                                    if (Integer.parseInt(num) != 0) {
                                        HashMap<String, String> hashMap = new HashMap<>();
                                        hashMap.put("DishName", dishname);
                                        hashMap.put("DishID", RandomId);
                                        hashMap.put("DishQuantity", String.valueOf(num));
                                        hashMap.put("Price", String.valueOf(dishprice));
                                        hashMap.put("Totalprice", String.valueOf(totalprice));
                                        hashMap.put("ChefId", ChefID);
                                        custID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                        reference = FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(custID).child(RandomId);
                                        reference.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {

                                                Toast.makeText(OrderDish.this, "Added to cart", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    } else {

                                        firebaseDatabase.getInstance().getReference("Cart").child(custID).child(RandomId).removeValue();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}