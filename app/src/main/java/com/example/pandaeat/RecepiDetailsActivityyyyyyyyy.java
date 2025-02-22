package com.example.pandaeat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pandaeat.Adapters.IngredientsAdapter;
import com.example.pandaeat.Adapters.InstructionAdapter;
import com.example.pandaeat.Adapters.SimiliarRecipiesAdapter;
import com.example.pandaeat.Listeners.InstructionListener;
import com.example.pandaeat.Listeners.RecipeClickListener;
import com.example.pandaeat.Listeners.RecipeDetailsListener;
import com.example.pandaeat.Listeners.SimiliarRecipesListener;
import com.example.pandaeat.Models.InstructionResponse;
import com.example.pandaeat.Models.RecipeDetailsResponse;
import com.example.pandaeat.Models.SimiliarRecipieResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecepiDetailsActivityyyyyyyyy extends AppCompatActivity {

    int id;
    TextView textView_meal_name,text_view_meal_source,textview_meal_summary;
    ImageView imageview_meal_image;
    RecyclerView recyler_meal_ingredients, recycler_meal_similar,recycler_meal_instruction;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;
    SimiliarRecipiesAdapter similiarRecipiesAdapter;
    InstructionAdapter instructionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepi_details_activityyyyyyyyy);

        findViews();


        id=Integer.parseInt(getIntent().getStringExtra("id"));

        manager=new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener,id);
        manager.getSimilarRecipes(similiarRecipesListener,id);

        manager.getInstructions(instructionListener,id);



        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Details...");
        dialog.show();





    }

    private void findViews() {
        recycler_meal_similar=findViewById(R.id.recycler_meal_similar);
        textView_meal_name=findViewById(R.id.textView_meal_name);
        text_view_meal_source=findViewById(R.id.text_view_meal_source);
        textview_meal_summary=findViewById(R.id.textview_meal_summary);
        imageview_meal_image=findViewById(R.id.imageview_meal_image);
        recyler_meal_ingredients=findViewById(R.id.recyler_meal_ingredients);
        recycler_meal_instruction=findViewById(R.id.recycler_meal_instruction);

    }

    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
        dialog.dismiss();
        textView_meal_name.setText(response.title);
        text_view_meal_source.setText(response.sourceName);
        textview_meal_summary.setText(response.summary);
        Picasso.get().load(response.image).into(imageview_meal_image);

        recyler_meal_ingredients.setHasFixedSize(true);
        recyler_meal_ingredients.setLayoutManager(new LinearLayoutManager(RecepiDetailsActivityyyyyyyyy.this, LinearLayoutManager.HORIZONTAL, false));
        ingredientsAdapter = new IngredientsAdapter(RecepiDetailsActivityyyyyyyyy.this, response.extendedIngredients);
        recyler_meal_ingredients.setAdapter(ingredientsAdapter);

        }

        @Override
        public void didError(String message) {

            Toast.makeText(RecepiDetailsActivityyyyyyyyy.this, message, Toast.LENGTH_SHORT).show();

        }
    };
    private final SimiliarRecipesListener similiarRecipesListener = new SimiliarRecipesListener() {
        @Override
        public void didFetch(List<SimiliarRecipieResponse> response, String message) {
            recycler_meal_similar.setHasFixedSize(true);
            recycler_meal_similar.setLayoutManager(new LinearLayoutManager(RecepiDetailsActivityyyyyyyyy.this,LinearLayoutManager.HORIZONTAL,false ));
            similiarRecipiesAdapter = new SimiliarRecipiesAdapter(RecepiDetailsActivityyyyyyyyy.this,response, recipeClickListener);
            recycler_meal_similar.setAdapter(similiarRecipiesAdapter);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecepiDetailsActivityyyyyyyyy.this, message, Toast.LENGTH_SHORT).show();
        }
    };


    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(RecepiDetailsActivityyyyyyyyy.this, RecepiDetailsActivityyyyyyyyy.class).putExtra("id", id));
            //Toast.makeText(RecepiDetailsActivityyyyyyyyy.this, id, Toast.LENGTH_SHORT).show();

        }
    };

    private final InstructionListener instructionListener = new InstructionListener() {
        @Override
        public void didFetch(List<InstructionResponse> response, String message) {
            recycler_meal_instruction.setHasFixedSize(true);
            recycler_meal_instruction.setLayoutManager(new LinearLayoutManager(RecepiDetailsActivityyyyyyyyy.this, LinearLayoutManager.VERTICAL, false));
            instructionAdapter= new InstructionAdapter(RecepiDetailsActivityyyyyyyyy.this, response);
            recycler_meal_instruction.setAdapter(instructionAdapter);
        }

        @Override
        public void didError(String message) {

        }
    };
}