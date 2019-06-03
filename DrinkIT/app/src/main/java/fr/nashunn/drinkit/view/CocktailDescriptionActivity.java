package fr.nashunn.drinkit.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.nashunn.drinkit.R;
import fr.nashunn.drinkit.model.Drink;

public class CocktailDescriptionActivity extends AppCompatActivity{
    private TextView tv_descName;
    private ImageView iv_cocktailImg;
    private TextView tv_cocktailDesc;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_description);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Put back button

        tv_descName = findViewById(R.id.tv_descName);
        iv_cocktailImg = findViewById(R.id.iv_cocktailImg);
        tv_cocktailDesc = findViewById(R.id.tv_description);

        Drink drink = (Drink) getIntent().getSerializableExtra("Cocktail");
        setViewItems(drink);
    }

    private void setViewItems(Drink drink) {
        tv_descName.setText(drink.getName());
        Picasso.get().load(drink.getIcon()).into(iv_cocktailImg);
        tv_cocktailDesc.setText(drink.getInstructions());
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
