package fr.afpa.covoiturafpa.model;

public class CarType {
    private int id;
    private CarTypeName carTypeName;
    private float avgFuelConsumption;
    
    enum CarTypeName {
        COMPACT,
        BERLINE,
        SUV,
        MONOSPACE,
        UTILITAIRE
    }

}