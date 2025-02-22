package com.example.pandaeat.chefFoodPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pandaeat.Famousfooddetails;
import com.example.pandaeat.MainMenu;
import com.example.pandaeat.R;
import com.example.pandaeat.RecepiMainActivity;
import com.example.pandaeat.UpdateDishModel;
import com.example.pandaeat.aboutus;
import com.example.pandaeat.teacher;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChefHomeFragment extends Fragment {

    RecyclerView recyclerView;
    private List<UpdateDishModel> updateDishModelList;
    private ChefHomeAdapter adapter;
    DatabaseReference dataa;
    private String State,City,Area;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chef_home,null);
        getActivity().setTitle("Home");
        setHasOptionsMenu(true);

        recyclerView = v.findViewById(R.id.Recycle_menu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        updateDishModelList = new ArrayList<>();
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dataa = FirebaseDatabase.getInstance().getReference("Chef").child(userid);
        dataa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Chef cheff = snapshot.getValue(Chef.class);
                State = cheff.getState();
                City = cheff.getCity();
                Area = cheff.getArea();
                chefDishes();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return v;
    }

    private void chefDishes() {
        String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("FoodDetails").child(State).child(City).child(Area).child(useridd);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              updateDishModelList.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    UpdateDishModel updateDishModel = snapshot1.getValue(UpdateDishModel.class);
                    updateDishModelList.add(updateDishModel);
                }
                adapter = new ChefHomeAdapter(getContext(),updateDishModelList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.logout,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idd = item.getItemId();
        if(idd==R.id.LOGOUT)
        {
            Logout();
            return true;
        }
        if(idd==R.id.profilewhite)
        {
            startActivity(new Intent(getActivity(),showprofile.class ));
        }
        if(idd==R.id.teacher)
        {
            startActivity(new Intent(getActivity(), teacher.class ));
        }
        if(idd==R.id.aboutus)
        {
            startActivity(new Intent(getActivity(), aboutus.class ));
        }
        if(idd==R.id.banglajson)
        {
            startActivity(new Intent(getActivity(), Famousfooddetails.class ));
        }
        if(idd==R.id.apiuse)
        {
            startActivity(new Intent(getActivity(), RecepiMainActivity.class ));
        }
        return super.onOptionsItemSelected(item);
    }

    private void Logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), MainMenu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
