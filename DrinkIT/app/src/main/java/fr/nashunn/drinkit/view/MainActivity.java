package fr.nashunn.drinkit.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import fr.nashunn.drinkit.R;
import fr.nashunn.drinkit.controller.MainControllerAPI;
import fr.nashunn.drinkit.data.SingletonAPI;
import fr.nashunn.drinkit.model.CocktailListAdapter;
import fr.nashunn.drinkit.model.Drink;

public class MainActivity extends AppCompatActivity {
    private RecyclerView cocktailRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CocktailListAdapter cocktailListAdapter;
    private static List<Drink> dataInList;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set controller
        MainControllerAPI controller = new MainControllerAPI(this, SingletonAPI.getInstance());
        controller.searchCocktailByName("apple");
    }

    /*
     * Update information of the list
     */
    public void updateCocktailList(List<Drink> data){
        layoutManager = new LinearLayoutManager(this);
        // Initalize a linear layout manager

        // Set view components
        cocktailRecyclerView = (RecyclerView) findViewById(R.id.cocktail_recylerView);
        cocktailRecyclerView.setLayoutManager(layoutManager);
        cocktailRecyclerView.setHasFixedSize(true);


        // specify an adapter (see also next example)
        cocktailListAdapter = new CocktailListAdapter(this, data);
        cocktailRecyclerView.setAdapter(cocktailListAdapter);
    }
}
