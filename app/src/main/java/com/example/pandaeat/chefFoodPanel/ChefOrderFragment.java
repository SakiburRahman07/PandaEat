package com.example.pandaeat.chefFoodPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pandaeat.R;

public class ChefOrderFragment extends Fragment {

    TextView OrdertobePrepare, Preparedorders;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chef_orders,null);
        getActivity().setTitle("New Orders");
        OrdertobePrepare=(TextView)v.findViewById(R.id.ordertobe);
        Preparedorders=(TextView)v.findViewById(R.id.prepareorder);

        OrdertobePrepare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),ChefOrderTobePrepared.class);
                Toast.makeText(getContext(),    "Orders to be prepared", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        Preparedorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ChefPreparedOrder.class);
                Toast.makeText(getContext(),    "Prepared Orders", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        return v;
    }
}
