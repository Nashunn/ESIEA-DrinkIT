package fr.nashunn.drinkit.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Drink {
    @SerializedName("strDrink")
    private String name;
    @SerializedName("strCategory")
    private String category;
    @SerializedName("strAlcoholic")
    private String isAlcoholic;
    @SerializedName("strGlass")
    private String glassType;
    @SerializedName("strInstructions")
    private String instructions;
    @SerializedName("strDrinkThumb")
    private String icon;

    //@SerializedName("strIngredient1")
    //private ArrayList ingredients; // todo : get list of ingredients
    //@SerializedName("strMeasure1")
    //private ArrayList measures; // todo : get list of measures

    public Drink() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIsAlcoholic() {

        return isAlcoholic;
    }

    public void setIsAlcoholic(String isAlcoholic) {
        this.isAlcoholic = isAlcoholic;
    }

    public String getGlassType() {
        return glassType;
    }

    public void setGlassType(String glassType) {
        this.glassType = glassType;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    /*public ArrayList getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList getMeasures() {
        return measures;
    }

    public void setMeasures(ArrayList measures) {
        this.measures = measures;
    }*/
}
