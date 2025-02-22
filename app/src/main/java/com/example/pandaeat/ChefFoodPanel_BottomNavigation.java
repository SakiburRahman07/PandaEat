package com.example.pandaeat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.pandaeat.chefFoodPanel.ChefHomeFragment;
import com.example.pandaeat.chefFoodPanel.ChefOrderFragment;
import com.example.pandaeat.chefFoodPanel.ChefPendingOrdersFragment;
import com.example.pandaeat.chefFoodPanel.ChefProfileFragment;
import com.example.pandaeat.deliveryFoodPanel.DeliveryPendingOrderFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class ChefFoodPanel_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
  //  private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_food_panel_bottom_navigation);
        BottomNavigationView navigationView = findViewById(R.id.chef_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        String name = getIntent().getStringExtra("PAGE");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = new ChefHomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();






















        if(name!=null)
        {
            if(name.equalsIgnoreCase("Orderpage"))
            {
                loadcheffragment(new ChefPendingOrdersFragment());
            }else if(name.equalsIgnoreCase("Confirmpage"))
            {
                loadcheffragment(new ChefOrderFragment());
            }else if(name.equalsIgnoreCase("AcceptOrderpage"))
            {
                loadcheffragment(new ChefOrderFragment());
            }
            else if(name.equalsIgnoreCase("Deliveredpage"))
            {
                loadcheffragment(new ChefOrderFragment());
            }
            else {
                loadcheffragment(new ChefHomeFragment());
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;


        if(item.getItemId()==R.id.chefHome)
        {
            fragment=new ChefHomeFragment();
        }

        if(item.getItemId()==R.id.PendingOrders)
        {
            fragment=new ChefPendingOrdersFragment();
        }

        if(item.getItemId()==R.id.Orders)
        {
            fragment=new ChefOrderFragment();
        }

        if(item.getItemId()==R.id.chefProfile)
        {
            fragment=new ChefProfileFragment();
        }


        /*
        switch ((item.getItemId()))
        {
            case R.id.chefHome:
                fragment=new ChefHomeFragment();
                break;

            case R.id.PendingOrders:
                fragment=new ChefPendingOrderFragment();
                break;

            case R.id.Orders:
                fragment=new ChefOrderFragment();
                break;

            case R.id.chefProfile:
                fragment=new ChefProfileFragment();
                break;
        }
*/
        return loadcheffragment(fragment);
    }

    private boolean loadcheffragment(Fragment fragment) {

        if(fragment!=null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment ).commit();
            return true;
        }
        return false;
    }
}