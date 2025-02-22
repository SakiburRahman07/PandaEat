package com.example.pandaeat;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;


import com.example.pandaeat.deliveryFoodPanel.DeliveryPendingOrderFragment;
import com.example.pandaeat.deliveryFoodPanel.DeliveryShipOrderFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class DeliveryFoodPanel_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_food_panel_bottom_navigation);
        BottomNavigationView navigationView = findViewById(R.id.delivery_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        Fragment fragment = new DeliveryPendingOrderFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerbott,fragment).commit();
        String name = getIntent().getStringExtra("PAGE");
        if(name!=null)
        {
            if(name.equalsIgnoreCase("DeliveryOrderpage")){
                loaddeliveryfragment(new DeliveryPendingOrderFragment());
            }
            else {
                loaddeliveryfragment(new DeliveryPendingOrderFragment());
            }
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        if(item.getItemId()==R.id.shiporders)
        {
            fragment=new DeliveryShipOrderFragment();
        }

        if(item.getItemId()==R.id.pendingorders)
        {
            fragment=new DeliveryPendingOrderFragment();
        }

      /*  switch (item.getItemId()){
            case R.id.shiporders:
                fragment=new DeliveryShipOrderFragment();
                break;
        }
        switch (item.getItemId()){
            case R.id.pendingorders:
                fragment=new DeliveryPendingOrderFragment();
                break;
        }*/
        return loaddeliveryfragment(fragment);
    }

    private boolean loaddeliveryfragment(Fragment fragment) {
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerbott,fragment).commit();
            return true;
        }
        return false;
    }
}
