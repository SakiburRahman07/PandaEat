package com.example.pandaeat.Listeners;

import com.example.pandaeat.Models.InstructionResponse;
import com.example.pandaeat.Models.RecipeDetailsResponse;

import java.util.List;

public interface InstructionListener {
    void didFetch(List<InstructionResponse> response, String message);
    void didError(String message);
}
