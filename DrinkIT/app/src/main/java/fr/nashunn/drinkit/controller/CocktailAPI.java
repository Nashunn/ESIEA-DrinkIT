package fr.nashunn.drinkit.controller;

import java.util.List;

import fr.nashunn.drinkit.model.Drink;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CocktailAPI {

    @GET("search.php?/")
    Call<List<Drink>> loadChanges(@Query("q") String status);
}
