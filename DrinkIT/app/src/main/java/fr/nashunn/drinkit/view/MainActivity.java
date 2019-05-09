package fr.nashunn.drinkit.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.nashunn.drinkit.R;
import fr.nashunn.drinkit.controller.MainControllerAPI;
import fr.nashunn.drinkit.data.SingletonAPI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainControllerAPI controller = new MainControllerAPI(this, SingletonAPI.getInstance());
        controller.searchCocktailByName("margarita");

        setContentView(R.layout.activity_main);
    }

    private void updateCocktailList(){

    }
}
