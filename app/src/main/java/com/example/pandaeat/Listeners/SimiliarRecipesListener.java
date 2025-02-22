package com.example.pandaeat.Listeners;

import com.example.pandaeat.Models.RecipeDetailsResponse;
import com.example.pandaeat.Models.SimiliarRecipieResponse;

import java.util.List;

public interface SimiliarRecipesListener {
    void didFetch(List<SimiliarRecipieResponse> response, String message);
    void didError(String message);
}
