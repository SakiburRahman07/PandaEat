package com.example.pandaeat.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pandaeat.Models.Step;
import com.example.pandaeat.R;

import java.util.List;

public class InstructionStepAdapter extends RecyclerView.Adapter<InstructionStepViewHolder>{
  Context context;
  List<Step> list;

    public InstructionStepAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_steps,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepViewHolder holder, int position) {

        holder.textview_instruction_step_number.setText(String.valueOf(list.get(position).number));
        holder.textview_instruction_step_title.setText(list.get(position).step);
        holder.recyler_instruction_ingredient.setHasFixedSize(true);
        holder.recyler_instruction_ingredient.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        InstructionIngredientAdapter instructionIngredientAdapter = new InstructionIngredientAdapter(context,list.get(position).ingredients);
        holder.recyler_instruction_ingredient.setAdapter(instructionIngredientAdapter);
        holder.recyler_instruction_equipment.setHasFixedSize(true);
        holder.recyler_instruction_equipment.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        InstructionEquipmentAdapter instructionEquipmentsAdapter = new InstructionEquipmentAdapter(context,list.get(position).equipment);
        holder.recyler_instruction_equipment.setAdapter(instructionEquipmentsAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionStepViewHolder extends RecyclerView.ViewHolder{
TextView textview_instruction_step_number,textview_instruction_step_title;
RecyclerView recyler_instruction_equipment,recyler_instruction_ingredient;
    public InstructionStepViewHolder(@NonNull View itemView) {
        super(itemView);
        textview_instruction_step_number=itemView.findViewById(R.id.textview_instruction_step_number);
        textview_instruction_step_title=itemView.findViewById(R.id.textview_instruction_step_title);
        recyler_instruction_equipment=itemView.findViewById(R.id.recyler_instruction_equipment);
        recyler_instruction_ingredient=itemView.findViewById(R.id.recyler_instruction_ingredient);
    }
}
