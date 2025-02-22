package com.example.pandaeat.deliveryFoodPanel;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.pandaeat.Famousfooddetails;
import com.example.pandaeat.MainMenu;
import com.example.pandaeat.R;
import com.example.pandaeat.RecepiMainActivity;
import com.example.pandaeat.aboutus;
import com.example.pandaeat.chefFoodPanel.showprofile;
import com.example.pandaeat.teacher;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeliveryPendingOrderFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<DeliveryShipOrders1> deliveryShipOrders1List;
    private DeliveryPendingOrderFragmentAdapter adapter;
    private DatabaseReference databaseReference;
    private SwipeRefreshLayout swipeRefreshLayout;
    String deliveryId = "oCpc4SwLVFbKO0fPdtp4R6bmDmI3";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deliveryprndingorder,null);
        getActivity().setTitle("Pending Orders");
        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.delipendingorder);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        deliveryShipOrders1List = new ArrayList<>();
        swipeRefreshLayout = view.findViewById(R.id.Swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.green);
        adapter = new DeliveryPendingOrderFragmentAdapter(getContext(), deliveryShipOrders1List);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                deliveryShipOrders1List.clear();
                recyclerView = view.findViewById(R.id.delipendingorder);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                deliveryShipOrders1List = new ArrayList<>();
                DeliveryPendingOrders();
            }
        });
        DeliveryPendingOrders();
        return view;
    }

    private void DeliveryPendingOrders() {
        databaseReference = FirebaseDatabase.getInstance().getReference("DeliveryShipOrders").child(deliveryId);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    deliveryShipOrders1List.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                DeliveryShipOrders1 deliveryShipOrders1 = snapshot.child("OtherInformation").getValue(DeliveryShipOrders1.class);
                                deliveryShipOrders1List.add(deliveryShipOrders1);
                                adapter = new DeliveryPendingOrderFragmentAdapter(getContext(), deliveryShipOrders1List);
                                recyclerView.setAdapter(adapter);
                                swipeRefreshLayout.setRefreshing(false);
                    }
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.logout, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int idd = item.getItemId();
        if (idd == R.id.LOGOUT) {
            Logout();
            return true;
        }
        if(idd==R.id.profilewhite)
        {
            startActivity(new Intent(getActivity(), deliveryprofile.class));
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
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

}
