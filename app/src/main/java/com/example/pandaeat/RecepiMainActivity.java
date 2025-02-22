package com.example.pandaeat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pandaeat.Adapters.RandomRecipeAdapter;
import com.example.pandaeat.Listeners.RandomRecipeResponseListener;
import com.example.pandaeat.Listeners.RecipeClickListener;
import com.example.pandaeat.Models.RandomRecipeApiResponse;

import java.util.ArrayList;
import java.util.List;

public class RecepiMainActivity extends AppCompatActivity {

    Spinner spinner;

    SearchView searchView;

    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;
    List<String> tags = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepi_main);

        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading...");
        searchView = findViewById(R.id.searchviewhomw);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRandomRecipes(randomRecipeResponseListener,tags);
                dialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        spinner = findViewById(R.id.spinnertags);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(
                this, R.array.tags, R.layout.spinnertext
        );

        arrayAdapter.setDropDownViewResource(R.layout.spinner_innertext);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerSelectedListener);

        manager=new RequestManager(this);
//        manager.getRandomRecipes(randomRecipeResponseListener);
//        dialog.show();
    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            recyclerView = findViewById(R.id.recycler_random);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(RecepiMainActivity.this, 1));
            randomRecipeAdapter = new RandomRecipeAdapter(RecepiMainActivity.this, response.recipes,recipeClickListener);
            recyclerView.setAdapter(randomRecipeAdapter);
            dialog.dismiss();
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecepiMainActivity.this, message, Toast.LENGTH_LONG).show();
System.out.println(message);
        }
    };


    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            tags.clear();
            tags.add(adapterView.getSelectedItem().toString());
            manager.getRandomRecipes(randomRecipeResponseListener,tags);
            dialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(RecepiMainActivity.this, RecepiDetailsActivityyyyyyyyy.class).putExtra("id", id));
            //Toast.makeText(RecepiMainActivity.this, id, Toast.LENGTH_SHORT).show();
        }
    };
}