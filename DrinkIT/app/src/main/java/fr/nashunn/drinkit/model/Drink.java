package fr.nashunn.drinkit.model;

import java.util.ArrayList;
import java.util.List;

public class Drink {
    private String name;
    private String category;
    private String isAlcoholic;
    private String glassType;
    private String instructions;
    // private Image illustration;
    private ArrayList ingredients;
    private ArrayList measures;

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

    public ArrayList getIngredients() {
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
    }
}
