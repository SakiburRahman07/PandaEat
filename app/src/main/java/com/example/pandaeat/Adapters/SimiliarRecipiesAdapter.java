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
import com.example.pandaeat.Models.SimiliarRecipieResponse;
import com.example.pandaeat.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimiliarRecipiesAdapter extends RecyclerView.Adapter<SimiliarRecipeViewHolder>{

    Context context;
    List<SimiliarRecipieResponse> list;
    RecipeClickListener listener;

    public SimiliarRecipiesAdapter(Context context, List<SimiliarRecipieResponse> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }


    @NonNull
    @Override
    public SimiliarRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimiliarRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_similar_recepe, parent, false));


    }

    @Override
    public void onBindViewHolder(@NonNull SimiliarRecipeViewHolder holder, int position) {
    holder.textview_similar_title.setText(list.get(position).title);
    holder.textview_similar_title.setSelected(true);
    holder.textviewsimilar_name.setText(list.get(position).servings+" Persons");
        Picasso.get().load("https://spoonacular.com/recipeImages/"+list.get(position).id+"-556x370."+list.get(position).imageType).into(holder.imageviewsimilar_);
        holder.similarrecipeholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class SimiliarRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView similarrecipeholder;
    ImageView imageviewsimilar_;
    TextView textviewsimilar_name,textview_similar_title;

    public SimiliarRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        similarrecipeholder=itemView.findViewById(R.id.similarrecipeholder);
        imageviewsimilar_=itemView.findViewById(R.id.imageviewsimilar_);
        textviewsimilar_name=itemView.findViewById(R.id.textviewsimilar_name);
        textview_similar_title=itemView.findViewById(R.id.textview_similar_title);

    }
}
