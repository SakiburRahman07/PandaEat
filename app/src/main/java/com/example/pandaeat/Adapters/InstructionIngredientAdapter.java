package com.example.pandaeat.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pandaeat.Models.Ingredient;
import com.example.pandaeat.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionIngredientAdapter extends RecyclerView.Adapter<InstructionIngredientsViewHolder> {
    Context context;
    List<Ingredient> list;

    public InstructionIngredientAdapter(Context context, List<Ingredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionIngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionIngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_step_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull InstructionIngredientsViewHolder holder, int position) {
       holder.textview_instruction_step_name.setText(list.get(position).name);
       holder.textview_instruction_step_name.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.imageview_instruction_step_item);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionIngredientsViewHolder extends RecyclerView.ViewHolder {
    ImageView imageview_instruction_step_item;
    TextView textview_instruction_step_name;
    public InstructionIngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        imageview_instruction_step_item=itemView.findViewById(R.id.imageview_instruction_step_item);
        textview_instruction_step_name=itemView.findViewById(R.id.textview_instruction_step_name);
    }
}
