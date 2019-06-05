package fr.nashunn.drinkit.view;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

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
    private MainControllerAPI controller;
    private Activity currentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentActivity = this;

        // Set fav button clickable
        btn_fav = findViewById(R.id.btn_fav);
        setBtnFavClick(this);

        // Set controller
        controller = new MainControllerAPI(this, SingletonAPI.getInstance());
        controller.searchCocktailByName(""); // Default research

        // Set search bar
        et_research = findViewById(R.id.et_research);
        setResearchListener();
    }

    private void setBtnFavClick(final Context context) {
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

    private void setResearchListener() {
        et_research.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Search cocktail with the value entered
                    controller.searchCocktailByName(et_research.getText().toString());
                    et_research.setText(""); // Clear editText
                    hideKeyboard(currentActivity);
                    return true;
                }
                return false;
            }
        });
    }

    private static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
