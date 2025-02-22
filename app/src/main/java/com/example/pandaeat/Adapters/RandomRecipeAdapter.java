package com.example.pandaeat.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pandaeat.Listeners.RecipeClickListener;
import com.example.pandaeat.Models.Recipe;
import com.example.pandaeat.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RanRecipeViewHolder>{

    Context context;
    List<Recipe> list;
    RecipeClickListener listener;

    public RandomRecipeAdapter(Context context, List<Recipe> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }



    @NonNull
    @Override
    public RanRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RanRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RanRecipeViewHolder holder, int position) {
        holder.textView_title.setText(list.get(position).title);
        holder.textView_title.setSelected(true);
        holder.textView_likes.setText(list.get(position).aggregateLikes+" Likes");
        holder.textviewserving.setText(list.get(position).servings+" Servings");
        holder.textView_time.setText(list.get(position).readyInMinutes+" Minutes");
        Picasso.get().load(list.get(position).image).into(holder.imageView_food);
        holder.random_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecipeClicked((String.valueOf(list.get(holder.getAdapterPosition()).id)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class RanRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView random_list_container;
    TextView textView_title,textView_likes,textView_time,textviewserving;
    ImageView imageView_food;

    public RanRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        random_list_container=itemView.findViewById(R.id.random_list_container);
        textView_title=itemView.findViewById(R.id.textview_title);
        textView_likes=itemView.findViewById(R.id.textview_likes);
        textView_time=itemView.findViewById(R.id.textview_time);
        imageView_food=itemView.findViewById(R.id.imageview_food);
        textviewserving=itemView.findViewById(R.id.textviewserving);


    }
}
