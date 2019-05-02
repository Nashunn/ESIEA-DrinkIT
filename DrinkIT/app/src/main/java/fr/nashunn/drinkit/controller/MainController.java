package fr.nashunn.drinkit.controller;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.nashunn.drinkit.data.CocktailAPI;
import fr.nashunn.drinkit.data.ResponseAPI;
import fr.nashunn.drinkit.model.Drink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController implements Callback<ResponseAPI> {
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

        Call<ResponseAPI> call = cocktailAPI.loadDrinks("name:margarita");

        call.enqueue(this);

        System.out.println("DEBUG NICO : START CONTROLLER");
    }

    public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
        ResponseAPI responseAPI = response.body();

        if(response.isSuccessful()) {
            List<Drink> drinksList = responseAPI.getDatalist();
            for (Drink drink : drinksList) {
                System.out.println("DEBUG NICO : drink :"+drink.getName());
            }
        } else {
            System.out.println(response.errorBody());
        }
    }

    public void onFailure(Call<ResponseAPI> call, Throwable t) {
        System.out.println("DEBUG NICO : FAIL CALL");
        System.out.println("No result");
        t.printStackTrace();
    }
}
