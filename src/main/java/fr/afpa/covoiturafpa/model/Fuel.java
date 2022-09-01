package fr.afpa.covoiturafpa.model;

public class Fuel {
    private int id;
    private String name;
    private float priceByUnit;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPriceByUnit() {
        return priceByUnit;
    }

    public void setPriceByUnit(float priceByUnit) {
        this.priceByUnit = priceByUnit;
    }

    public Fuel() {
    }
}
