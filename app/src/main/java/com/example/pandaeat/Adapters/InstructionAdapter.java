package com.example.pandaeat.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pandaeat.Models.InstructionResponse;
import com.example.pandaeat.R;

import java.util.List;

public class InstructionAdapter extends RecyclerView.Adapter<InstructionViewHolder>{

    Context context;
    List<InstructionResponse> list;

    public InstructionAdapter(Context context, List<InstructionResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction, parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull InstructionViewHolder holder, int position) {
    holder.textview_instruction_name.setText(list.get(position).name);
    holder.recycler_instruction_step.setHasFixedSize(true);
    holder.recycler_instruction_step.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
    InstructionStepAdapter stepAdapter = new InstructionStepAdapter(context,list.get(position).steps);
    holder.recycler_instruction_step.setAdapter(stepAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionViewHolder extends RecyclerView.ViewHolder{
    TextView textview_instruction_name;
    RecyclerView recycler_instruction_step;

    public InstructionViewHolder(@NonNull View itemView) {
        super(itemView);
        recycler_instruction_step=itemView.findViewById(R.id.recycler_instruction_step);

        textview_instruction_name=itemView.findViewById(R.id.textview_instruction_name);
    }
}
