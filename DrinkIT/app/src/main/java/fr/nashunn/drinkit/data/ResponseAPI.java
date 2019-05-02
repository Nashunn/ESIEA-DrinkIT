package fr.nashunn.drinkit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import fr.nashunn.drinkit.model.Drink;

public class ResponseAPI {
    @SerializedName("drinks")
    private List<Drink> drinks;

    public List<Drink> getDatalist() {
        return drinks;
    }
}
