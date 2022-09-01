package fr.afpa.covoiturafpa.model;

public class CarType {
    private int id;
    private CarTypeName carTypeName;
    private float avgFuelConsumption;
    private Fuel fuel;
    

    enum CarTypeName {
        COMPACT,
        BERLINE,
        SUV,
        MONOSPACE,
        UTILITAIRE
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarTypeName getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(CarTypeName carTypeName) {
        this.carTypeName = carTypeName;
    }

    public float getAvgFuelConsumption() {
        return avgFuelConsumption;
    }

    public void setAvgFuelConsumption(float avgFuelConsumption) {
        this.avgFuelConsumption = avgFuelConsumption;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public CarType() {
    }
}