package fr.nashunn.drinkit.controller;

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

        Call<List<Drink>> call = cocktailAPI.loadChanges("status:open");
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<Drink>> call, Response<List<Drink>> response) {
        if(response.isSuccessful()) {
            List<Drink> DrinksList = response.body();
            for (Drink drink : DrinksList) {
                System.out.println(drink.getName());
            }
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Drink>> call, Throwable t) {
        t.printStackTrace();
    }
}
