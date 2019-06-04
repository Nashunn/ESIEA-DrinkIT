package fr.nashunn.drinkit.view;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.FloatingActionButton;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private EditText et_research;
    private FloatingActionButton btn_fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Search bar
        et_research = findViewById(R.id.et_research);

        // Set fav button clickable
        btn_fav = findViewById(R.id.btn_fav);
        setBtnFavClick(this);

        // Set controller
        MainControllerAPI controller = new MainControllerAPI(this, SingletonAPI.getInstance());
        controller.searchCocktailByName("apple");
    }

    public void setBtnFavClick(final Context context) {
        btn_fav.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, FavoritesActivity.class);
                context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle()); // With transition
            }
        });
    }

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
