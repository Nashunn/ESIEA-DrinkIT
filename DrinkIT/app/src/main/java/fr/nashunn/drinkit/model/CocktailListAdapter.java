package fr.nashunn.drinkit.model;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.nashunn.drinkit.R;

/*
 * Adaptater used by the recyclerview
 */
public class CocktailListAdapter extends RecyclerView.Adapter<CocktailViewHolder> {
    private List<Drink> dataset;
    private Context context;

    public CocktailListAdapter(Context context, List<Drink> drinkList) {
        this.dataset = drinkList;
        this.context = context;
    }

    // Return size of dataset
    @Override
    public int getItemCount() {
        return dataset.size();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CocktailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view item
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.cocktail_row_layout, parent, false);
        CocktailViewHolder vh = new CocktailViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CocktailViewHolder holder, int position) {
        // Get the drink at indicated position in the dataset
        final Drink currentDrink = dataset.get(position);
        holder.setCurrentDrink(currentDrink);

        Picasso.get().load(currentDrink.getIcon()).into(holder.iv_image);
        holder.tv_name.setText(currentDrink.getName());
        holder.tv_isAlcoholic.setText(currentDrink.getIsAlcoholic());

        // If drink is not alcoholic, change the color
        if(!currentDrink.getIsAlcoholic().equals("Alcoholic"))
            ImageViewCompat.setImageTintList(holder.iv_alcoholIndicator, ColorStateList.valueOf(Color.argb(255, 50, 200, 100)));
    }

}
