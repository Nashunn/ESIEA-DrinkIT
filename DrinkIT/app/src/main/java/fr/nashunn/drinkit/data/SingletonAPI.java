package fr.nashunn.drinkit.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingletonAPI {
    static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/";
    private static CocktailAPI instance;

    public static CocktailAPI getInstance() {
        // If instance doesn't exist, create it before using it
        if(instance == null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            // Create the instance of CocktailAPI
            instance = retrofit.create(CocktailAPI.class);
        }
        //Set instance of api
        return instance;
    }
}
