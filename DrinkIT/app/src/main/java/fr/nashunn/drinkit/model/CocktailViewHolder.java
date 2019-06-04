package fr.nashunn.drinkit.model;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import fr.nashunn.drinkit.R;
import fr.nashunn.drinkit.view.CocktailDescriptionActivity;

public class CocktailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final Context context;
    public View layout;
    public ImageView iv_image;
    public TextView tv_name;
    public ImageView iv_alcoholIndicator;
    public TextView tv_isAlcoholic;
    public Drink currentDrink;

    public CocktailViewHolder(View itemView) {
        super(itemView);
        layout = itemView;
        tv_name = itemView.findViewById(R.id.tv_name);
        iv_alcoholIndicator = itemView.findViewById(R.id.iv_alcoholIndicator);
        tv_isAlcoholic = itemView.findViewById(R.id.tv_isAlcoholic);
        iv_image = itemView.findViewById(R.id.cocktailIcon);

        context = itemView.getContext(); // Get context to do the onClick

        // Setting onClick
        itemView.setClickable(true);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, CocktailDescriptionActivity.class);
        intent.putExtra("Cocktail", currentDrink);
        context.startActivity(intent);
    }

    public void setCurrentDrink(Drink drink) {
        this.currentDrink = drink;
    }
}