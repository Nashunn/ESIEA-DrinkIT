package fr.nashunn.drinkit.data;

import java.util.List;

import fr.nashunn.drinkit.model.Drink;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CocktailAPI {
    @GET("search.php?/")
    Call<ResponseAPI> loadDrinks(@Query("s") String name);

    @GET("random.php")
    Call<ResponseAPI> loadRandomDrinks();
}
