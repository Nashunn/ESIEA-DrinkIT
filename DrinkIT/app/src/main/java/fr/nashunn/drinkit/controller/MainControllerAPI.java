package fr.nashunn.drinkit.controller;

import android.widget.Toast;

import java.util.List;

import fr.nashunn.drinkit.data.CocktailAPI;
import fr.nashunn.drinkit.data.ResponseAPI;
import fr.nashunn.drinkit.model.Drink;
import fr.nashunn.drinkit.view.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainControllerAPI {
    private MainActivity activity;
    private CocktailAPI api;

    public MainControllerAPI(MainActivity activity, CocktailAPI api) {
        this.activity = activity;
        this.api = api;
    }

    public void searchCocktailByName(String name) {
        Call<ResponseAPI> call = api.loadDrinks(name); // Search cocktail by name
        // Put call in async queue
        call.enqueue(
            new Callback<ResponseAPI>() {
                //On response
                public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                    ResponseAPI responseAPI = response.body(); // Deserialize response body into objects

                    if(response.isSuccessful()) {
                        List<Drink> drinks = responseAPI.getDrinks();
                        if(response.isSuccessful()) {
                            // Send data to view
                            activity.updateCocktailList(drinks);
                        }
                        else {
                            System.out.println(response.errorBody());
                        }
                    } else {
                        Toast.makeText(activity.getBaseContext(), "ERROR : API did not respond successfully", Toast.LENGTH_LONG).show();
                        System.out.println(response.errorBody());
                    }
                }

                // On failure
                public void onFailure(Call<ResponseAPI> call, Throwable t) {
                    Toast.makeText(activity.getBaseContext(), "ERROR : API not reachable", Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            }
        );
    }
}
