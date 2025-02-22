package com.example.pandaeat.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pandaeat.Models.Equipment;
import com.example.pandaeat.Models.Ingredient;
import com.example.pandaeat.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionEquipmentAdapter extends RecyclerView.Adapter<InstructionEquipmentViewHolder> {
    Context context;
    List<Equipment> list;

    public InstructionEquipmentAdapter(Context context, List<Equipment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionEquipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionEquipmentViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_step_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull InstructionEquipmentViewHolder holder, int position) {
       holder.textview_instruction_step_name.setText(list.get(position).name);
       holder.textview_instruction_step_name.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/equipment_100x100/"+list.get(position).image).into(holder.imageview_instruction_step_item);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionEquipmentViewHolder extends RecyclerView.ViewHolder {
    ImageView imageview_instruction_step_item;
    TextView textview_instruction_step_name;
    public InstructionEquipmentViewHolder(@NonNull View itemView) {
        super(itemView);
        imageview_instruction_step_item=itemView.findViewById(R.id.imageview_instruction_step_item);
        textview_instruction_step_name=itemView.findViewById(R.id.textview_instruction_step_name);
    }
}
