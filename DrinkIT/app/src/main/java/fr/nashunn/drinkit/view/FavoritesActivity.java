package fr.nashunn.drinkit.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fr.nashunn.drinkit.R;
import fr.nashunn.drinkit.model.CocktailListAdapter;
import fr.nashunn.drinkit.model.Drink;

public class FavoritesActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Put back button

        // Update list with data from sharedPreference
        sharedPreferences = this.getSharedPreferences(getString(R.string.pref_favorites), Context.MODE_PRIVATE); // Get fav file in cache
        updateFavList(getFavList());
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateFavList(getFavList());
    }

    private List<Drink> getFavList() {
        List<Drink> result = new LinkedList<>();
        Map<String, ?> favorites = sharedPreferences.getAll();

        for (Map.Entry<String, ?> fav : favorites.entrySet()) {
            Type typeDrink = new TypeToken<Drink>(){}.getType();
            String strDrink = fav.getValue().toString();

            Drink currentDrink = new Gson().fromJson(strDrink, typeDrink);
            result.add(currentDrink);
        }

        return result;
    }

    public void updateFavList(List<Drink> data){
        // Initalize a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // Set view components
        RecyclerView favoritesRecyclerView = findViewById(R.id.favorites_recylerView);
        favoritesRecyclerView.setLayoutManager(layoutManager);
        favoritesRecyclerView.setHasFixedSize(true);


        // specify an adapter (see also next example)
        CocktailListAdapter favoritesListAdapter = new CocktailListAdapter(this, data);
        favoritesRecyclerView.setAdapter(favoritesListAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

