package fr.nashunn.drinkit.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import fr.nashunn.drinkit.R;

public class CocktailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final Context context;
    public View layout;
    public ImageView iv_image;
    public TextView tv_name;

    public CocktailViewHolder(View itemView) {
        super(itemView);
        layout = itemView;
        tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        iv_image = (ImageView) itemView.findViewById(R.id.cocktailIcon);

        context = itemView.getContext(); // Get context to do the onClick

        // Setting onClick
        itemView.setClickable(true);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context,"The item clicked is : "+tv_name.getText(),Toast.LENGTH_LONG).show();
    }
}