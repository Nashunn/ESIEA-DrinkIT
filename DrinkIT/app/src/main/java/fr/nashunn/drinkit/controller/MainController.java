package fr.nashunn.drinkit.controller;

import android.widget.Toast;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.nashunn.drinkit.model.Drink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController implements Callback<List<Drink>> {
    static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/";

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CocktailAPI cocktailAPI = retrofit.create(CocktailAPI.class);

        Call<List<Drink>> call = cocktailAPI.loadDrinks("name:margarita");

        call.enqueue(this);

        System.out.println("DEBUG NICO : START CONTROLLER");
    }

    @Override
    public void onResponse(Call<List<Drink>> call, Response<List<Drink>> response) {
        System.out.println("DEBUG NICO : On response begin");
        if(response.isSuccessful()) {
            List<Drink> DrinksList = response.body();
            for (Drink drink : DrinksList) {
                System.out.println("DEBUG NICO : drink :"+drink.getName());
            }
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Drink>> call, Throwable t) {
        System.out.println("DEBUG NICO : FAIL CALL");
        System.out.println("No result");
        t.printStackTrace();
    }
}
