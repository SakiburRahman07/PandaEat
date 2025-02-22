package com.example.pandaeat;

import android.content.Context;

import com.example.pandaeat.Listeners.InstructionListener;
import com.example.pandaeat.Listeners.RandomRecipeResponseListener;
import com.example.pandaeat.Listeners.RecipeDetailsListener;
import com.example.pandaeat.Listeners.SimiliarRecipesListener;
import com.example.pandaeat.Models.InstructionResponse;
import com.example.pandaeat.Models.RandomRecipeApiResponse;
import com.example.pandaeat.Models.RecipeDetailsResponse;
import com.example.pandaeat.Models.SimiliarRecipieResponse;
import com.google.android.datatransport.runtime.retries.Retries;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getRandomRecipes(RandomRecipeResponseListener listener, List<String> tags){
        CallRandomRecipes callRandomRecipes=retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key),"25",tags);
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {

                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
               listener.didFetch(response.body(),response.message() );



            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
            listener.didError(t.getMessage());
            }
        });
    }

    public void getRecipeDetails(RecipeDetailsListener listener, int id)
    {
        CallRecipeDetails callRecipeDetails=retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsResponse> call= callRecipeDetails.callRecipeDetails(id,context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getSimilarRecipes(SimiliarRecipesListener listener, int id)
    {
        CallSimiliarRecipes callSimiliarRecipes=retrofit.create(CallSimiliarRecipes.class);
        Call<List<SimiliarRecipieResponse>> call = callSimiliarRecipes.callSimiliarRecipe(id, "4", context.getString(R.string.api_key));
        call.enqueue(new Callback<List<SimiliarRecipieResponse>>() {
            @Override
            public void onResponse(Call<List<SimiliarRecipieResponse>> call, Response<List<SimiliarRecipieResponse>> response) {
                if(!response.isSuccessful())
                {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<SimiliarRecipieResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }


public void getInstructions(InstructionListener listener, int id)
{
    CallInstructions callInstructions = retrofit.create(CallInstructions.class);
    Call<List<InstructionResponse>> call = callInstructions.callInstructions(id, context.getString(R.string.api_key));
    call.enqueue(new Callback<List<InstructionResponse>>() {
        @Override
        public void onResponse(Call<List<InstructionResponse>> call, Response<List<InstructionResponse>> response) {
            if(!response.isSuccessful())
            {
                listener.didError(response.message());
                return;
            }
            listener.didFetch(response.body(), response.message());
        }

        @Override
        public void onFailure(Call<List<InstructionResponse>> call, Throwable t) {
            listener.didError(t.getMessage());
        }
    });
}


    private interface CallRandomRecipes{
        @GET("recipes/random")
    Call<RandomRecipeApiResponse> callRandomRecipe(
            @Query("apiKey") String apiKey,
            @Query("number") String number,
            @Query("tags") List<String> tags
    );
    }

    private interface CallRecipeDetails{
        @GET("recipes/{id}/information")
        Call<RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

    private interface CallSimiliarRecipes {
        @GET("recipes/{id}/similar")
        Call<List<SimiliarRecipieResponse>> callSimiliarRecipe(
                @Path("id") int id,
                @Query("number") String number,
                @Query("apiKey") String apiKey
        );
    }


    private interface CallInstructions{
        @GET("recipes/{id}/analyzedInstructions")
        Call<List<InstructionResponse>> callInstructions(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }


}
