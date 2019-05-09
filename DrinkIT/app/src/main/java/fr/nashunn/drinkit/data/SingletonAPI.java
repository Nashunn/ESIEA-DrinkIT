package fr.nashunn.drinkit.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingletonAPI {
    static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/";
    private static CocktailAPI instanceAPI;

    public static CocktailAPI getInstance() {
        // If instanceAPI doesn't exist, create it before using it
        if(instanceAPI == null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            // Create the instanceAPI of CocktailAPI
            instanceAPI = retrofit.create(CocktailAPI.class);
        }
        //Set instanceAPI of api
        return instanceAPI;
    }
}
