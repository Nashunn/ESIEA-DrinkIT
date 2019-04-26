package fr.nashunn.drinkit.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.nashunn.drinkit.R;
import fr.nashunn.drinkit.controller.MainController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainController controller = new MainController();
        controller.start();

        setContentView(R.layout.activity_main);
    }
}
