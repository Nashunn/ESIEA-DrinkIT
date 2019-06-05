package fr.nashunn.drinkit.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import fr.nashunn.drinkit.R;
import fr.nashunn.drinkit.model.Drink;

public class CocktailDescriptionActivity extends AppCompatActivity{
    private TextView tv_descName;
    private ImageView iv_cocktailImg;
    private ImageView iv_favoriteIndicator;
    private TextView tv_cocktailDesc;
    private TextView tv_ingredientsList;
    private String list_ingredients;
    private SharedPreferences sharedPreferences;
    private boolean isFav = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_description);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Put back button
        sharedPreferences = this.getSharedPreferences(getString(R.string.pref_favorites), Context.MODE_PRIVATE); // Get fav file in cache

        Drink drink = (Drink) getIntent().getSerializableExtra("Cocktail");
        setViewItems(drink);
    }

    private void setViewItems(Drink drink) {
        tv_descName = findViewById(R.id.tv_descName);
        iv_cocktailImg = findViewById(R.id.iv_cocktailImg);
        iv_favoriteIndicator = findViewById(R.id.iv_favoriteIndicator);
        tv_cocktailDesc = findViewById(R.id.tv_description);
        tv_ingredientsList = findViewById(R.id.tv_ingredientsList);

        initFavBtn(drink);
        list_ingredients = makeListIngredients(drink);

        tv_descName.setText(drink.getName());
        Picasso.get().load(drink.getIcon()).into(iv_cocktailImg);
        tv_cocktailDesc.setText(drink.getInstructions());
        tv_ingredientsList.setText(list_ingredients);
    }

    private void initFavBtn(final Drink drink) {
        // Set boolean and color if drink is in fav
        if(sharedPreferences.contains(drink.getId())) {
            iv_favoriteIndicator.setColorFilter(Color.argb(255, 63, 140, 181));
            isFav = !isFav;
        }

        // Set on click
        iv_favoriteIndicator.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                actionOnClickFav(drink);
            }
        });
    }

    private void actionOnClickFav(Drink drink) {
        if(isFav) {
            sharedPreferences.edit()
                .remove(drink.getId())
            .apply();

            iv_favoriteIndicator.clearColorFilter();
        }
        else {
            Gson oGson = new Gson();
            String drinkStr = oGson.toJson(drink);
            sharedPreferences.edit()
                .putString(drink.getId(), drinkStr)
            .apply();

            iv_favoriteIndicator.setColorFilter(Color.argb(255, 63, 140, 181));
        }

        isFav = !isFav; // Invert boolean
    }

    private String makeListIngredients(Drink drink) {
        String result = "";
        int iIngredient = 0;
        boolean isFinished = false;

        while(iIngredient<=15 && isFinished==false ) {
            String prefix_getIngredient = "getIngredient";
            String prefix_getMeasure = "getMeasure";
            String ingredientStr = "";
            String measureStr = "";
            iIngredient++;

            // Invoke method
            try {
                ingredientStr = (String) Drink.class.getMethod(prefix_getIngredient+iIngredient).invoke(drink);
                measureStr = (String) Drink.class.getMethod(prefix_getMeasure+iIngredient).invoke(drink);
            } catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException e) {
                e.printStackTrace();
            }

            if(ingredientStr == null || ingredientStr.equals("")) {
                result = result.substring(0, result.length() - 3);
                isFinished = true;
            }
            else
                if(measureStr == null || measureStr.equals(""))
                    result += "- "+ingredientStr+"\r\n";
                else
                    result += "- "+ingredientStr+" : "+measureStr+"\r\n";
        }

        return result;
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
