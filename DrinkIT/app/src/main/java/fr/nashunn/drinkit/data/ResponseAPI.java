package fr.nashunn.drinkit.data;

import java.util.List;

import fr.nashunn.drinkit.model.Drink;

public class ResponseAPI {
    private List<Drink> drinks;

    public List<Drink> getDrinks() {
        return this.drinks;
    }
}
